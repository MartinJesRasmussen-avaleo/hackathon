package net.avaleo.hackathon.controllers;

import net.avaleo.hackathon.listener.SocketListener;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ras on 25-09-14.
 */
@Configuration
public class SessionContainer {
    private List<SocketListener> sessions = new ArrayList<SocketListener>();

    public SessionContainer() {
        System.out.println("SESSION CONTAINER INITIALIZED");
    }

    public void addSession(SocketListener session) {
        sessions.add(session);
    }

    public List<SocketListener> getSessions() {
        return sessions;
    }
}
