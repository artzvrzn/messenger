package com.artzvrzn.service.messenger.api;

import com.artzvrzn.service.messenger.api.dto.Chat;

import java.util.List;

public interface IMessenger {

    Chat createChat(String name, String admin);

    Chat getChat(String id);

    List<Chat> getChats();

}
