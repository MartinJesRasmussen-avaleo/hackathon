package net.avaleo.hackathon.listener;

import net.avaleo.hackathon.dao.AbstractEsperListener;
import net.avaleo.hackathon.events.Event;
import org.springframework.web.socket.WebSocketSession;

public class SocketListener extends AbstractEsperListener {

    WebSocketSession session;

    @Override
    public void doUpdate(Event event) {

    }

    public void setSession(WebSocketSession session) {
        this.session = session;
    }
}
