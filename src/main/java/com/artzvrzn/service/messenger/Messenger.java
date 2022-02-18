package com.artzvrzn.service.messenger;

import com.artzvrzn.service.messenger.api.IMessenger;
import com.artzvrzn.service.messenger.api.dto.Chat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Messenger implements IMessenger {

    private static final IMessenger INSTANCE = new Messenger();

    private final Map<String, Chat> chats = new HashMap<>();

    private Messenger() {}

    public static IMessenger getInstance() {
        return INSTANCE;
    }

    @Override
    public Chat createChat(String name, String admin) {
        String id = UUID.randomUUID().toString();
        Chat chat = new Chat(id, name, admin);
        chats.put(id, chat);
        return chat;
    }

    @Override
    public Chat getChat(String id) {
        return chats.get(id);
    }

    @Override
    public List<Chat> getChats() {
        return List.copyOf(chats.values());
    }
}
