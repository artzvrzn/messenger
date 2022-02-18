<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<html>
    <head>
        <title>Sign in</title>
    </head>
    <body>
        <h4>Вход на сайт</h4>
        <p style="color: red"> ${error} </p>
        <form action="${pageContext.request.contextPath}/sign_in" method="post">
            <p>Логин: <input type="text" name="username"> </p>
            <p>Пароль: <input type="password" name="password"> </p>
            <input type="submit" value="Вход">
        </form>
        <form action="${pageContext.request.contextPath}/sign_up">
            <input type="submit" value="Зарегистрироваться">
        </form>
    </body>
</html>