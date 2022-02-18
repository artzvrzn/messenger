<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <title>New chat</title>
    </head>
    <body>
        <h4> Создать чат </h4>
        <p style="color: red"> ${error} </p>
        <form action="${pageContext.request.contextPath}/messenger/new_chat" method="POST" >
            <p>Название: <input type="text" name="chatName"> </p>
            <c:set value="${applicationScope['userService'].getUsers()}" var="users"/>
            <p>Участники:
                <select name="participants" multiple="multiple" size="4">
                <c:forEach var="participant" items="${users}" >
                    <c:if test="${user.username != participant.username}">
                        <option value="${participant.username}"> ${participant.username} </option>
                    </c:if>
                </c:forEach>
                </select>
            </p>
            <input type="submit" value="Начать чат">
        </form>
        <form action="${pageContext.request.contextPath}/messenger">
            <input type="submit" value="Вернуться">
        </form>
    </body>
</html>