<%--
  Created by IntelliJ IDEA.
  User: kellyss
  Date: 06/10/2020
  Time: 23:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign in</title>
</head>
<body>
<form action="/signin" method="post">
    <div class="container">
        <h1>Вход</h1>
        <hr>
        <b>Login</b>
        <input type="text" placeholder="Enter login" name="login" required>
        <b>Password</b>
        <input type="password" placeholder="Enter Password" name="password" required>
        <button type="submit">
            SignIn
        </button>
    </div>

</form>
</body>
</html>
