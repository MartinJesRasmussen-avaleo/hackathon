package net.avaleo.hackathon.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * Created by ras on 25-09-14.
 */

public class MessageRequestHandler extends TextWebSocketHandler {
    @Autowired
    private SessionContainer sessionContainer;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        TextMessage msg = new TextMessage("this is the payload reply to " + message);
        sessionContainer.addSession(session);
    }
}
