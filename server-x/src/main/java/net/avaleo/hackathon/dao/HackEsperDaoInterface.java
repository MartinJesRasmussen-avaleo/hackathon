package net.avaleo.hackathon.dao;

import net.avaleo.hackathon.events.Event;
import org.springframework.context.annotation.Configuration;


public interface HackEsperDaoInterface {

    public void publish(Event event);

    public void registerListener(AbstractEsperListener listener);

}
