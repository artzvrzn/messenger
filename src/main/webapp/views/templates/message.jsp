<fmt:parseDate value="${message.sendingTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsed" type="both"/>
<tr>
    <td><fmt:formatDate value="${parsed}" pattern="HH:mm"/></td>
    <td><a href="${pageContext.request.contextPath}/user?username=${message.sender}"> ${message.sender} </a></td>
    <td>${message.text}</td>
</tr>