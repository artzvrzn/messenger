package com.artzvrzn.controllers.web.servlets.auth;

import com.artzvrzn.service.auth.api.IUsers;
import com.artzvrzn.service.auth.api.dto.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserServlet", urlPatterns = "/user")
public class UserServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IUsers users = (IUsers) req.getServletContext().getAttribute("userService");
        String username = req.getParameter("username");
        User user = users.getUser(username);
        if (user == null) {
            req.setAttribute("error", "Пользователя" + username + " не существует");
        } else {
            req.setAttribute("username", user.getUsername());
            req.setAttribute("userInfo", user.getInfo());
        }
        req.getRequestDispatcher("/views/auth/user.jsp").forward(req, resp);
    }
}
