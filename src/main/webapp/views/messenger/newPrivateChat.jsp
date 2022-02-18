<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<html>
    <head>
        <title>Private chat</title>
    </head>
    <body>
        <h4> Отправить сообщение </h4>
        <p style="color: red"> ${error} </p>
        <form action="${pageContext.request.contextPath}/messenger/new_private_chat" method="post">
            <p>Логин получателя: <input type="text" name="recipient"> </p>
            <p>Сообщение: <input type="text" name="message"> </p>
            <input type="submit" value="Начать диалог">
        </form>
        <form action="${pageContext.request.contextPath}/messenger">
            <input type="submit" value="Вернуться">
        </form>
    </body>
</html>