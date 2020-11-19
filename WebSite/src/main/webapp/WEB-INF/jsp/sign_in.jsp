<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Marsel
  Date: 23.09.2020
  Time: 12:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Вход</title>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Roboto:wght@100&display=swap');
    </style>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="../../css/sty.css"/>

</head>
<body>
<form action="/signIn" method="post">
    <label for="l1">Email</label>
    <input type="email" id="l1" name="email" placeholder="example@email.com">
    <label for="l4">Пароль</label>
    <input type="password" id="l4" name="password" placeholder="">
    <button type="submit" class="btn btn-secondary">Войти</button>
</form>
<form action="/signUp" method="get">
    <button class="btn btn-secondary"> Создать аккаунт </button>
</form>
</body>
</html>
