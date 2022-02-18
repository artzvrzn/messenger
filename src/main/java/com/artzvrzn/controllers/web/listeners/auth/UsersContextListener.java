package com.artzvrzn.controllers.web.listeners.auth;

import com.artzvrzn.service.auth.Users;
import com.artzvrzn.service.auth.api.IUsers;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class UsersContextListener implements ServletContextListener {

    private final IUsers userService;

    public UsersContextListener() {
        this.userService = Users.getInstance();
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        servletContextEvent.getServletContext().setAttribute("userService", userService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {}
}
