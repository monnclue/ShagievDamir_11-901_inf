<%--
  Created by IntelliJ IDEA.
  User: Marsel
  Date: 28.10.2020
  Time: 14:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Мой профиль</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="../../css/sty.css"/>
</head>
<body>
    <h1>Профиль!</h1>
    <form action="/logout" method="POST">
        <button class="btn btn-secondary"> Выйти </button>
    </form>
    <form action="/order" method="get">
        <button class="btn btn-secondary"> Мои заказы </button>
    </form>
</body>
</html>
