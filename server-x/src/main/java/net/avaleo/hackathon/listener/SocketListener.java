package net.avaleo.hackathon.listener;

import net.avaleo.hackathon.ObjectMapper;
import net.avaleo.hackathon.dao.AbstractEsperListener;
import net.avaleo.hackathon.events.Event;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

public class SocketListener extends AbstractEsperListener {

    private final WebSocketSession session;
	private final ObjectMapper objectMapper;

	public SocketListener(WebSocketSession session, ObjectMapper objectMapper) {
		this.session = session;
		this.objectMapper = objectMapper;
	}

	@Override
    public void doUpdate(Event event) throws Exception {
        String msg = objectMapper.writeValueAsString(event);
        TextMessage reply = new TextMessage(msg);
        session.sendMessage(reply);
    }
}
