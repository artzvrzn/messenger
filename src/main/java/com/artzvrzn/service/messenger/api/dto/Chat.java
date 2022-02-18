package com.artzvrzn.service.messenger.api.dto;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Chat implements Serializable {

    private final String id;
    private final String name;
    private final String admin;
    private final Set<String> participants;
    private final Queue<Message> messages;
    private boolean privacy;

    public Chat(String id, String name, String admin) {
        this.id = id;
        this.name = name;
        this.admin = admin;
        participants = new HashSet<>();
        participants.add(admin);
        messages = new ConcurrentLinkedDeque<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAdmin() {
        return admin;
    }

    public Set<String> getParticipants() {
        return Collections.unmodifiableSet(participants);
    }

    public void addParticipant(String username) {
        if (!privacy) {
            participants.add(username);
        }
    }

    public void removeParticipant(String username) {
        participants.remove(username);
    }

    public List<Message> getMessages() {
        return List.copyOf(messages);
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public boolean isPrivate() {
        return privacy;
    }

    public void setPrivacy(boolean privacy) {
        this.privacy = privacy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Chat)) return false;
        Chat chat = (Chat) o;
        return id.equals(chat.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                ", admin='" + admin + '\'' +
                '}';
    }
}
