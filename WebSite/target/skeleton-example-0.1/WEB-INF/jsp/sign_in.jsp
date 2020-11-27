<%@ page import="java.util.Arrays" %>
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
    <link rel="stylesheet" type="text/css" href="../../css/night.css"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="../../css/sty.css"/>
    <link rel="stylesheet" type="text/css" href="../../css/signIn.css"/>


</head>
<body>
<script>
    <%
    String mode;
    if(Arrays.stream(request.getCookies())
    .noneMatch(cookie -> cookie.getName().equals("mode"))) {
        mode = "day";
    } else {
        mode = Arrays.stream(request.getCookies())
        .filter(cookie -> cookie.getName().equals("mode")).findFirst().get().getValue();
    }
    %>
    let mode = '<%=mode%>';

    function toggleMode() {
        let body = document.getElementsByTagName("body").item(0);
        if (mode === 'night') body.classList.add('dark-mode');
        else body.classList.remove('dark-mode');
    }

    toggleMode();

</script>

<div id="signin-box">
    <form action="/signIn" method="post">
        <div class="form-group row">
            <label for="l1" class="col-sm-2 col-form-label">Email</label>
            <div class="col-sm-10">
                <input type="email" name="email" class="form-control" id="l1" placeholder="example@email.com">
            </div>
        </div>
        <div class="form-group row">
            <label for="l4" class="col-sm-2 col-form-label">Password</label>
            <div class="col-sm-10">
                <input type="password" name="password" class="form-control" id="l4" placeholder="Password">
            </div>
        </div>

        <button type="submit" class="btn btn-dark">Войти</button>

    </form>

</div>
<form id="signup-form" action="/signUp" method="get">
    <button id="signup-button" class="btn btn-dark"> Создать аккаунт </button>
</form>

</body>
</html>

