package net.avaleo.hackathon.dao;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import com.mongodb.*;
import net.avaleo.hackathon.events.Event;
import org.joda.time.DateTime;
import org.springframework.context.annotation.Configuration;

import java.net.UnknownHostException;
import java.util.Date;

@Configuration
public class HackEsperDao implements HackEsperDaoInterface {
    private final static EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider();

    private static final String EVENTS_COLLECTION = "events";
    private static final String MESSAGE_C = "message";
    private static final String SENDER_C = "sender";
    private static final String TIMESTAMP_C = "timestamp";

    private static final boolean USE_DB = true;

    private static MongoClient mongoClient;
    private static DB db;

    public HackEsperDao() throws UnknownHostException {
        mongoClient = new MongoClient("localhost");
        db = mongoClient.getDB("test");
    }

    @Override
    public void publish(Event event) {
        epService.getEPRuntime().sendEvent(event);

        if (USE_DB) {
            publishToMongoDb(event);
        }
    }

    private void publishToMongoDb(Event event) {
        DBCollection collection = db.getCollection(EVENTS_COLLECTION);

        BasicDBObject doc = new BasicDBObject(MESSAGE_C, event.getMessage()).
                append(SENDER_C, event.getSender()).
                append(TIMESTAMP_C, event.getTimestamp().toDate());

        collection.insert(doc);
    }

    @Override
    public void registerListener(AbstractEsperListener listener) {
        String expression = "select * from net.avaleo.hackathon.events.Event.win:time(30 sec)";
        EPStatement statement = epService.getEPAdministrator().createEPL(expression);
        statement.addListener(listener);
        
        if (USE_DB) {
            readHistory(listener);
        }
    }

    private void readHistory(AbstractEsperListener listener) {
        DBCollection collection = db.getCollection(EVENTS_COLLECTION);

        BasicDBObject query = new BasicDBObject("timestamp", new BasicDBObject("$gt", new DateTime().minusHours(1).toDate()));
        DBCursor dbObjects = collection.find(query);
        while (dbObjects.hasNext()) {
            DBObject object = dbObjects.next();
            Event event = new Event();
            event.setMessage((String) object.get(MESSAGE_C));
            event.setSender((String) object.get(SENDER_C));
            event.setTimestamp(new DateTime(object.get(TIMESTAMP_C)));

            try {
                listener.doUpdate(event);
            } catch (Exception e) {
                System.err.println(e.getStackTrace());
            }
        }
    }
}
