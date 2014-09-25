package net.avaleo.hackathon.dao;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import net.avaleo.hackathon.events.Event;

public class HackEsperDao implements HackEsperDaoInterface {
    private final static EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider();

    @Override
    public void publish(Event event) {

    }

    @Override
    public void registerListener(AbstractEsperListener listener) {

    }
}
