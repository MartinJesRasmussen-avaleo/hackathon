package net.avaleo.hackathon.dao;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;
import net.avaleo.hackathon.events.Event;

public abstract class AbstractEsperListener implements UpdateListener {

    @Override
    public void update(EventBean[] events, EventBean[] oldEvents) {
        try {
            doUpdate((Event) events[0].getUnderlying());
        } catch (Exception e) {
            System.err.println(e.getStackTrace());
        }
    }

    public abstract void doUpdate(Event event) throws Exception;
}
