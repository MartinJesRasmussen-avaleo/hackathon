package net.avaleo.hackathon.controllers;

import net.avaleo.hackathon.ObjectMapper;
import net.avaleo.hackathon.dao.HackEsperDaoInterface;
import net.avaleo.hackathon.events.Event;
import net.avaleo.hackathon.listener.SocketListener;
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
    private HackEsperDaoInterface webSocketDao;

	@Autowired
	private ObjectMapper objectMapper;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        SocketListener listener = new SocketListener(session, objectMapper);

		webSocketDao.registerListener(listener);
        super.handleTextMessage(session, message);
//        Event event = new Event();
//        event.setMessage("This is the body part of the message!");
//        event.setSender("Georg Holstrup");
//        event.setTimestamp(new DateTime());
//        ObjectMapper mapper = new ObjectMapper();
//        String msg = mapper.writeValueAsString(event);
//        TextMessage reply = new TextMessage(msg);

        //while (true) {
        //    try {
        //        Thread.sleep(5000);
                webSocketDao.publish(getEvent());
        //    } catch (InterruptedException e) {
        //        e.printStackTrace();
        //    }
        //}
    }

	private Event getEvent() {
		Event event = new Event();
		event.setMessage("This is the body part of the message (" + new DateTime().getSecondOfDay() + ")!");
		event.setSender("Georg Holstrup");
		event.setTimestamp(new DateTime());
		return event;
    }
}
