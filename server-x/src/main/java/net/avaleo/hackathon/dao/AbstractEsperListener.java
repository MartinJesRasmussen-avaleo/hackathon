package net.avaleo.hackathon.dao;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;
import net.avaleo.hackathon.events.Event;

public abstract class AbstractEsperListener implements UpdateListener {

    @Override
    public void update(EventBean[] events, EventBean[] oldEvents) {
        doUpdate((Event) events[0].getUnderlying());
    }

    public abstract void doUpdate(Event event);
}
