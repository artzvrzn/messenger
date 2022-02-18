package com.artzvrzn.controllers.web.servlets.messenger;

import com.artzvrzn.service.auth.api.IUsers;
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
import java.util.List;
import java.util.Optional;

@WebServlet(name = "StartPrivateChatServlet", urlPatterns = "/messenger/new_private_chat")
public class StartPrivateChatServlet extends HttpServlet {

    private final static String ERROR_ATTRIBUTE = "error";
    private final static String NEW_PRIVATE_CHAT_JSP = "/views/messenger/newPrivateChat.jsp";

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher(NEW_PRIVATE_CHAT_JSP).forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User user = (User) req.getSession().getAttribute("user");
        IUsers users = (IUsers) getServletContext().getAttribute("userService");
        String recipient = req.getParameter("recipient");
        String text = req.getParameter("message");
        if (recipient.isEmpty()) {
            req.setAttribute(ERROR_ATTRIBUTE, "Вы не указали получателя!");
            req.getRequestDispatcher(NEW_PRIVATE_CHAT_JSP).forward(req, resp);
        } else if (users.getUser(recipient) == null) {
            req.setAttribute(ERROR_ATTRIBUTE, "Такого пользователя не существует!");
            req.getRequestDispatcher(NEW_PRIVATE_CHAT_JSP).forward(req, resp);
        } else if (text.isEmpty()) {
            req.setAttribute(ERROR_ATTRIBUTE, "Сообщение не может быть пустым");
            req.getRequestDispatcher(NEW_PRIVATE_CHAT_JSP).forward(req, resp);
        } else {
            IMessenger messenger = (IMessenger) getServletContext().getAttribute("messengerService");
            Chat chat = getPrivateChat(user.getUsername(), recipient, messenger);
            chat.addParticipant(recipient);
            chat.setPrivacy(true);
            chat.addMessage(new Message(user.getUsername(), text));
            resp.sendRedirect("chat?chatId=" + chat.getId());
        }
    }

    private Chat getPrivateChat(String sender, String recipient, IMessenger messengerService) {
        Optional<Chat> optional = messengerService.getChats()
                .stream()
                .filter(Chat::isPrivate)
                .filter(c -> c.getParticipants().containsAll(List.of(sender, recipient)))
                .findFirst();
        if (optional.isEmpty()) {
            String chatName = sender + " & " + recipient;
            return messengerService.createChat(chatName, sender);
        }
        return optional.get();
    }
}
