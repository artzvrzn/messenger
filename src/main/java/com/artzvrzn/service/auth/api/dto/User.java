package com.artzvrzn.service.auth.api.dto;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String username;
    private final String password;
    private final UserInfo info;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.info = new UserInfo();
    }

    public String getUsername() {
        return username;
    }

    public boolean evaluatePassword(String password) {
        return this.password.equals(password);
    }

    public UserInfo getInfo() {
        return info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", info=" + info +
                '}';
    }
}