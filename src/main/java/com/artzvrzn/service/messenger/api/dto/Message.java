package com.artzvrzn.service.messenger.api.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;

public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String sender;
    private final String text;
    private final ZonedDateTime sendingTime;

    public Message(String sender, String text) {
        this.sender = sender;
        this.text = text;
        this.sendingTime = ZonedDateTime.now();
    }

    public String getSender() {
        return sender;
    }

    public String getText() {
        return text;
    }

    public ZonedDateTime getSendingTime() {
        return sendingTime;
    }

    @Override
    public String toString() {
        return "Message{" +
                "sender='" + sender + '\'' +
                ", text='" + text + '\'' +
                ", sentTime=" + sendingTime +
                '}';
    }
}
