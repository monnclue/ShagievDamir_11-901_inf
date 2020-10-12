<%@ page import="ru.itis.models.UserForCookieTask" %><%--
  Created by IntelliJ IDEA.
  User: kellyss
  Date: 07/10/2020
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Hello,
    <%  UserForCookieTask user = (UserForCookieTask) request.getAttribute("user");%>
    <%=user.getLogin()%>
</h1>
</body>

<form action="/logout" method="POST">
    <br/><input type="submit" value="Log out">
</form>

</html>

