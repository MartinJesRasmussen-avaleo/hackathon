package net.avaleo.hackathon.events;

import org.joda.time.DateTime;

/**
 * Created by ras on 25-09-14.
 */
public class Event {
    private String message;
    private String sender;
    private DateTime timestamp;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public DateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(DateTime timestamp) {
        this.timestamp = timestamp;
    }
}
