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

@WebServlet(name = "StartChatServlet", urlPatterns = "/messenger/new_chat")
public class StartChatServlet extends HttpServlet {

    private final static String NEW_CHAT_JSP = "/views/messenger/newChat.jsp";
    private final static String ERROR_ATTRIBUTE = "error";

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher(NEW_CHAT_JSP).forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User user = (User) req.getSession().getAttribute("user");
        String[] participants = req.getParameterValues("participants");
        String chatName = req.getParameter("chatName");
        if (participants == null || (participants.length == 1 && participants[0].equals(user.getUsername()))) {
            req.setAttribute(ERROR_ATTRIBUTE, "Не указаны собеседники!");
            req.getRequestDispatcher(NEW_CHAT_JSP).forward(req, resp);
        } else if (chatName.isEmpty()) {
            req.setAttribute(ERROR_ATTRIBUTE, "Не указано название чата!");
            req.getRequestDispatcher(NEW_CHAT_JSP).forward(req, resp);
        } else {
            IMessenger messenger = (IMessenger) getServletContext().getAttribute("messengerService");
            Chat chat = messenger.createChat(chatName, user.getUsername());
            for (String participant : participants) {
                chat.addParticipant(participant);
            }
            resp.sendRedirect("chat?chatId=" + chat.getId());
        }
    }
}
