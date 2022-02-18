package com.artzvrzn.controllers.web.filters.messenger;

import com.artzvrzn.service.auth.api.dto.User;
import com.artzvrzn.service.messenger.api.IMessenger;
import com.artzvrzn.service.messenger.api.dto.Chat;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChatFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String chatId = req.getParameter("chatId");
        if (chatId == null) {
            resp.sendRedirect(req.getContextPath() + "/messenger");
        }
        User user = (User) req.getSession().getAttribute("user");
        IMessenger messenger = (IMessenger) servletRequest.getServletContext().getAttribute("messengerService");
        Chat chat = messenger.getChat(chatId);
        if (chat != null && chat.getParticipants().contains(user.getUsername())) {
            filterChain.doFilter(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/messenger");
        }
    }

    @Override
    public void destroy() {

    }
}
