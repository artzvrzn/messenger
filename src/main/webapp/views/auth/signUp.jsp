<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<html>
    <head>
        <title>Sign up</title>
    </head>
    <body>
        <h4> Регистрация </h4>
        <p style="color: red"> ${error} </p>
        <form action="${pageContext.request.contextPath}/sign_up" method="post">
            <p>Логин*: <input type="text" name="username"> </p>
            <p>Имя: <input type="text" name="name"> </p>
            <p>Отчество: <input type="text" name="middleName"> </p>
            <p>Фамилия: <input type="text" name="lastname"> </p>
            <p>Дата рождения*: <input type="date" name="birth"> </p>
            <p>Пароль: <input type="password" name="password"> </p>
            <input type="submit" value="Регистрация">
        </form>
    </body>
</html>