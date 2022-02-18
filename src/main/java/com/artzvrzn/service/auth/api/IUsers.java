package com.artzvrzn.service.auth.api;

import com.artzvrzn.service.auth.api.dto.User;

import java.util.List;

public interface IUsers {

    User getUser(String username);

    void addUser(User user);

    void deleteUser(String username);

    List<User> getUsers();
}
