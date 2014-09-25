package net.avaleo.hackathon.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.avaleo.hackathon.dao.HackEsperDaoInterface;
import java.util.TimeZone;

import net.avaleo.hackathon.events.Event;
import net.avaleo.hackathon.listener.SocketListener;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;

/**
 * Created by ras on 25-09-14.
 */

public class CaseFlowMessagingWebSocket extends TextWebSocketHandler {
    @Autowired
    private HackEsperDaoInterface webSocketDao;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        SocketListener listener = new SocketListener();
        listener.setSession(session);
        webSocketDao.registerListener(listener);
        super.handleTextMessage(session, message);
//        Event event = new Event();
//        event.setMessage("This is the body part of the message!");
//        event.setSender("Georg Holstrup");
//        event.setTimestamp(new DateTime());
//        ObjectMapper mapper = new ObjectMapper();
//        String msg = mapper.writeValueAsString(event);
//        TextMessage reply = new TextMessage(msg);

		ObjectMapper mapper = getObjectMapper();

        while (true) {
			String msg = mapper.writeValueAsString(getEvent());
			TextMessage reply = new TextMessage(msg);
            session.sendMessage(reply);

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

	private ObjectMapper getObjectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setTimeZone(TimeZone.getDefault());
		mapper.registerModule(new JodaModule());
		return mapper;
	}

	private Event getEvent() {
		Event event = new Event();
		event.setMessage("This is the body part of the message (" + new DateTime().getSecondOfDay() + ")!");
		event.setSender("Georg Holstrup");
		event.setTimestamp(new DateTime());
		return event;
	}
}
