<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <title>Sign in</title>
    </head>
    <body>
        <h4>Данный о пользователе ${username}</h4>
        <p style="color: red"> ${error} </p>
        <p>Логин: ${username} </p>
        <p>ФИО: ${userInfo.lastname}&nbsp;${userInfo.name}&nbsp;${userInfo.middleName} </p>
        <p>Дата рождения:
            <fmt:parseDate value="${userInfo.birthDate}" pattern="yyyy-MM-dd" var="parsed" type="date"/>
            <fmt:formatDate value="${parsed}" pattern="dd.MM.yyyy"/>
        </p>
            <form action="${pageContext.request.contextPath}">
                <input type="submit" value="На главную">
            </form>
    </body>
</html>