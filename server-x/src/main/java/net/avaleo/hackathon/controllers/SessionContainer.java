package net.avaleo.hackathon.controllers;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ras on 25-09-14.
 */
@Configuration
public class SessionContainer {
    private List<WebSocketSession> sessions = new ArrayList<WebSocketSession>();

    public SessionContainer() {
        System.out.println("SESSION CONTAINER INITIALIZED");
    }

    public void addSession(WebSocketSession session) {
        sessions.add(session);
    }

    public List<WebSocketSession> getSessions() {
        return sessions;
    }
}
