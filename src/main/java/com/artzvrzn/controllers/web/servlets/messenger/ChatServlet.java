package com.artzvrzn.controllers.web.servlets.messenger;

import com.artzvrzn.service.auth.api.dto.User;
import com.artzvrzn.service.messenger.api.IMessenger;
import com.artzvrzn.service.messenger.api.dto.Chat;
import com.artzvrzn.service.messenger.api.dto.Message;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ChatServlet", urlPatterns = "/messenger/chat")
public class ChatServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String chatId = req.getParameter("chatId");
        IMessenger messenger = (IMessenger) getServletContext().getAttribute("messengerService");
        Chat chat = messenger.getChat(chatId);
        req.setAttribute("chat", chat);
        req.getRequestDispatcher("/views/messenger/chat.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = (User) req.getSession().getAttribute("user");
        String chatId = req.getParameter("chatId");
        IMessenger messenger = (IMessenger) getServletContext().getAttribute("messengerService");
        Chat chat = messenger.getChat(chatId);
        String text = req.getParameter("messageText");
        chat.addMessage(new Message(user.getUsername(), text));
        resp.sendRedirect("chat?chatId=" + chatId);
    }
}
