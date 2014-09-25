package net.avaleo.hackathon.dao;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import net.avaleo.hackathon.events.Event;

public class HackEsperDao implements HackEsperDaoInterface {
    private final static EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider();

    @Override
    public void publish(Event event) {
        epService.getEPRuntime().sendEvent(event);
    }

    @Override
    public void registerListener(AbstractEsperListener listener) {
        String expression = "select * from net.avaleo.hackathon.events.Event.win:time(30 sec)";
        EPStatement statement = epService.getEPAdministrator().createEPL(expression);
        statement.addListener(listener);
    }
}
