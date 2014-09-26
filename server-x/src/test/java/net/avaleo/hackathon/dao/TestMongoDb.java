package net.avaleo.hackathon.dao;

import com.mongodb.*;
import org.joda.time.DateTime;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.Set;

/**
 * Created by Michał on 2014-09-26.
 */
public class TestMongoDb {

    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient("localhost");
        DB db = client.getDB("test");


        DBCollection collection = db.getCollection("events");

        BasicDBObject query = new BasicDBObject("timestamp", new BasicDBObject("$gt", new DateTime().minusHours(1).toDate()));
        DBCursor dbObjects = collection.find(query);
        while (dbObjects.hasNext()) {
            DBObject object = dbObjects.next();
            System.out.println(object.get("message"));
        }

//        BasicDBObject doc = new BasicDBObject("message", "my message").
//                append("sender", "georg").
//                append("timestamp", new Date());
//
//        collection.insert(doc);
//
//
//        Set<String> collectionNames = db.getCollectionNames();
//
//        for (String name : collectionNames) {
//            System.out.println(name);
//        }




    }
}
