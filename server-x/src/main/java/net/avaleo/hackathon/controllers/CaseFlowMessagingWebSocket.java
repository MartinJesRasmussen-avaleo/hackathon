package net.avaleo.hackathon.controllers;

import net.avaleo.hackathon.ObjectMapper;
import net.avaleo.hackathon.dao.HackEsperDaoInterface;
import net.avaleo.hackathon.listener.SocketListener;

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
    }
}
