<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <title>Messenger</title>
    </head>
    <body>
        <h4> Messenger </h4>
        <p style="color: red"> ${error} </p>
        <form action="${pageContext.request.contextPath}/messenger/new_chat" method="get">
            <input type="submit" value="Создать новый чат">
        </form>
        <form action="${pageContext.request.contextPath}/messenger/new_private_chat" method="get">
                <input type="submit" value="Отправить личное сообщение">
        </form>

        <c:forEach var="chat" items="${chats}">
        <p>
            <form action="${pageContext.request.contextPath}/messenger/chat" method="get">
                <input type="submit" value="Перейти к чату">
                <input type="hidden" name="chatId" value="${chat.id}">
                <c:choose>
                    <c:when test="${chat.isPrivate()}">
                        <c:forEach items="${chat.participants}" var="participant">
                            <c:if test="${participant != user.username}">
                            ${participant}
                            </c:if>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        ${chat.name}
                    </c:otherwise>
                </c:choose>
            </form>
        </p>
        <c:set value="${chat.messages.size() - 1}" var="lastIndex"/>
        <c:set value="${chat.messages[lastIndex]}" var="message"/>
        <c:choose>
            <c:when test="${lastIndex >= 0}">
                <p>Последнее сообщение: <%@include file="/views/templates/message.jsp"%></p>
            </c:when>
            <c:otherwise>
                <p>Нет сообщений</p>
            </c:otherwise>
        </c:choose>
        </c:forEach>
        <form action="${pageContext.request.contextPath}">
           <input type="submit" value="На главную">
        </form>
    </body>
</html>