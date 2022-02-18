<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <title>${chat.name}</title>
    </head>
    <body>
        <h4>${chat.name}</h4>
        <p style="color: red"> ${error} </p>
        <form action="${pageContext.request.contextPath}/messenger">
            <input type="submit" value="К списку чатов">
        </form>
        <table>
            <c:forEach items="${chat.messages}" var="message">
                <%@include file="/views/templates/message.jsp"%>
            </c:forEach>
        </table>
        <form method="POST" action="${pageContext.request.contextPath}/messenger/chat">
            <input type="hidden" name="chatId" value="${chat.id}">
            <p><input type="text" name="messageText"> <input type="submit" value="Написать"></p>
        </form>
    </body>
</html>