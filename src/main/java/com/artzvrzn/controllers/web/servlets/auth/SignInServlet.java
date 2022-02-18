package com.artzvrzn.controllers.web.servlets.auth;

import com.artzvrzn.service.auth.api.IUsers;
import com.artzvrzn.service.auth.api.dto.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SignInServlet", urlPatterns = "/sign_in")
public class SignInServlet extends HttpServlet {

    private final static String SIGN_IN_JSP = "/views/auth/signIn.jsp";

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher(SIGN_IN_JSP).forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        IUsers userService = (IUsers) getServletContext().getAttribute("userService");
        User user = userService.getUser(username);
        if (user == null || !user.evaluatePassword(password)) {
            req.setAttribute("error", "Неверный логин или пароль!");
            req.getRequestDispatcher(SIGN_IN_JSP).forward(req, resp);
        } else {
            req.getSession().setAttribute("user", user);
            resp.sendRedirect(req.getContextPath());
        }
    }

}
