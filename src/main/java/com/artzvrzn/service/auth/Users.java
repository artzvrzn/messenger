package com.artzvrzn.service.auth;

import com.artzvrzn.service.auth.api.IUsers;
import com.artzvrzn.service.auth.api.dto.User;

import java.util.*;

public class Users implements IUsers {

    private static final IUsers INSTANCE = new Users();

    private final Map<String, User> users = new HashMap<>();

    private Users() {}

    public static IUsers getInstance() {
        return INSTANCE;
    }

    @Override
    public User getUser(String username) {
        return users.get(username);
    }

    @Override
    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    @Override
    public void deleteUser(String username) {
        users.remove(username);
    }

    @Override
    public List<User> getUsers() {
        return List.copyOf(users.values());
    }
}
