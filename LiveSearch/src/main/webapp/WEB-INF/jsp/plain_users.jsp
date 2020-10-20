<%@ page import="ru.itis.models.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Marsel
  Date: 23.09.2020
  Time: 12:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <tr>
        <th>ID</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Age</th>
    </tr>

    <%
        List<User> users = (List<User>) request.getAttribute("usersForJsp");
        for (int i = 0; i < users.size(); i++) {
    %>
    <tr>
        <td><%=users.get(i).getId()%></>
        <td><%=users.get(i).getFirstName()%></>
        <td><%=users.get(i).getLastName()%></>
        <td><%=users.get(i).getAge()%></>
    </tr>
    <%}%>
</table>
</body>
</html>
