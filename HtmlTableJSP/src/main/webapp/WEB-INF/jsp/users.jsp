<%--
  Created by IntelliJ IDEA.
  User: kellyss
  Date: 27/09/2020
  Time: 19:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>users</title>
</head>
<body>

<table>
    <tr>
        <th>id</th>
        <th>first name</th>
        <th>last name</th>
        <th>age</th>
    </tr>

    <c:forEach items="${usersForJsp}" var ="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.age}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
