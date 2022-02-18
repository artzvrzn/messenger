package com.artzvrzn.controllers.web.listeners.messenger;

import com.artzvrzn.service.messenger.Messenger;
import com.artzvrzn.service.messenger.api.IMessenger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MessengerContextListener implements ServletContextListener {

    private final IMessenger messengerService;

    public MessengerContextListener() {
        this.messengerService = Messenger.getInstance();
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        servletContextEvent.getServletContext().setAttribute("messengerService", messengerService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {}
}
