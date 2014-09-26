package net.avaleo.hackathon.dao;

import net.avaleo.hackathon.events.Event;
import org.joda.time.DateTime;

import java.net.UnknownHostException;

/**
 * Created by Michał on 2014-09-25.
 */
class TestHackEsperDao {

    public static void main(String[] args) throws UnknownHostException {
        HackEsperDao dao = new HackEsperDao();

        Event event = new Event();
        event.setMessage("Message");
        event.setSender("Sender");
        event.setTimestamp(new DateTime());

        dao.registerListener(new AbstractEsperListener() {
            @Override
            public void doUpdate(Event getEvent) {
                System.out.println("get:"+getEvent.getMessage());
            }
        });

        dao.publish(event);


    }

}
