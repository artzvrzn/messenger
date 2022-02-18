package com.artzvrzn.controllers.web.servlets.auth;

import com.artzvrzn.service.auth.api.IUsers;
import com.artzvrzn.service.auth.api.dto.User;
import com.artzvrzn.service.auth.api.dto.UserInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "SignUpServlet", urlPatterns = "/sign_up")
public class SignUpServlet extends HttpServlet {

    private final static String SIGN_UP_JSP = "views/auth/signUp.jsp";
    private final static String ERROR_ATTRIBUTE = "error";

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher(SIGN_UP_JSP).forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String username = req.getParameter("username");
        IUsers userService = (IUsers) getServletContext().getAttribute("userService");
        String date = req.getParameter("birth");
        if (username.isEmpty()) {
            req.setAttribute(ERROR_ATTRIBUTE, "Не указано имя пользователя!");
            req.getRequestDispatcher(SIGN_UP_JSP).forward(req, resp);
        } else if (userService.getUser(username) != null) {
            req.setAttribute(ERROR_ATTRIBUTE, "Такой пользователь уже существует");
            req.getRequestDispatcher(SIGN_UP_JSP).forward(req, resp);
        } else if (date.isEmpty()) {
            req.setAttribute(ERROR_ATTRIBUTE, "Не указана дата рождения!");
            req.getRequestDispatcher(SIGN_UP_JSP).forward(req, resp);
        } else {
            String password = req.getParameter("password");
            LocalDate birthDate = LocalDate.parse(date);
            User user = new User(username, password);
            UserInfo info = user.getInfo();
            info.setBirthDate(birthDate);
            info.setName(req.getParameter("name"));
            info.setMiddleName(req.getParameter("middleName"));
            info.setLastname(req.getParameter("lastname"));
            userService.addUser(user);
            req.getSession().setAttribute("user", user);
            resp.sendRedirect(req.getContextPath());
        }
    }

}
