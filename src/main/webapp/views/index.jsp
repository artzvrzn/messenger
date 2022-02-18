<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
    <head>
        <title>Main page</title>
    </head>
    <body>
        <h4>Main page</h4>
        <p style="color: red"> ${error} </p>
        <form action="${pageContext.request.contextPath}/messenger">
            <input type="submit" value="Messenger">
        </form>
       <c:choose>
           <c:when test="${undefinedUser}">
               <form action="${pageContext.request.contextPath}/sign_up">
                   <input type="submit" value="Регистрация">
               </form>
               <form action="${pageContext.request.contextPath}/sign_in">
                   <input type="submit" value="Вход">
               </form>
           </c:when>
           <c:otherwise>
              <form action="${pageContext.request.contextPath}/user">
                  <input type="hidden" value="${sessionScope.user.username}" name="username">
                  <input type="submit" value="Информация о пользователе">
              </form>
               <form action="${pageContext.request.contextPath}/" method="post">
                   <input type="submit" value="Выйти">
               </form>
           </c:otherwise>
       </c:choose>

    </body>
</html>