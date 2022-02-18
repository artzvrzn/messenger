package com.artzvrzn.controllers.web.servlets.messenger;

import com.artzvrzn.service.auth.api.dto.User;
import com.artzvrzn.service.messenger.api.IMessenger;
import com.artzvrzn.service.messenger.api.dto.Chat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "MessengerServlet", urlPatterns = "/messenger")
public class MessengerServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        IMessenger messenger = (IMessenger) getServletContext().getAttribute("messengerService");
        List<Chat> userChats = getUserChats(user.getUsername(), messenger);
        req.setAttribute("chats", userChats);
        req.getRequestDispatcher("/views/messenger/index.jsp").forward(req, resp);
    }

    private List<Chat> getUserChats(String username, IMessenger messenger) {
        return messenger.getChats()
                .stream()
                .filter(c -> c.getParticipants().contains(username))
                .collect(Collectors.toList());
    }
}
