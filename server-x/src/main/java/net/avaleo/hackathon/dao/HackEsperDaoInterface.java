package net.avaleo.hackathon.dao;

import com.espertech.esper.client.UpdateListener;
import net.avaleo.hackathon.events.Event;

public interface HackEsperDaoInterface {

    public void publish(Event event);

    public void registerListener(AbstractEsperListener listener);

}
