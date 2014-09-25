package net.avaleo.hackathon.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.avaleo.hackathon.events.Event;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * Created by ras on 25-09-14.
 */

public class CaseFlowMessagingWebSocket extends TextWebSocketHandler {
    @Autowired
    private SessionContainer sessionContainer;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        sessionContainer.addSession(session);
        super.handleTextMessage(session, message);
        Event event = new Event();
        event.setMessage("This is the body part of the message!");
        event.setSender("Georg Holstrup");
        event.setTimestamp(new DateTime());
        ObjectMapper mapper = new ObjectMapper();
        String msg = mapper.writeValueAsString(event);
        TextMessage reply = new TextMessage(msg);

        while (true) {
            session.sendMessage(reply);

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
