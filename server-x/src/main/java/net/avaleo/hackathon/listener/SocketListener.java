package net.avaleo.hackathon.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import net.avaleo.hackathon.dao.AbstractEsperListener;
import net.avaleo.hackathon.events.Event;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.TimeZone;

public class SocketListener extends AbstractEsperListener {

    WebSocketSession session;

    @Override
    public void doUpdate(Event event) throws Exception {
        String msg = getObjectMapper().writeValueAsString(event);
        TextMessage reply = new TextMessage(msg);
        session.sendMessage(reply);
    }

    private ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setTimeZone(TimeZone.getDefault());
        mapper.registerModule(new JodaModule());
        return mapper;
    }

    public void setSession(WebSocketSession session) {
        this.session = session;
    }
}
