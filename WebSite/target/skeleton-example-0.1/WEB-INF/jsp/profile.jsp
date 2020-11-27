<%@ page import="java.util.Arrays" %><%--
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
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Roboto:wght@100&display=swap');
    </style>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="../../css/sty.css"/>
    <link rel="stylesheet" type="text/css" href="../../css/night.css">


    <script
            src="https://code.jquery.com/jquery-3.5.1.js"
            integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
            crossorigin="anonymous"></script>
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

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/shop">AWESOME SHOP</a>
    <button class="navbar-toggler" type="button" id="burger" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item" id="cart-button">
                <a class="nav-link" href="/cart">Корзина</a>
            </li>

        </ul>


    </div>

</nav>

    <form action="/logout" method="POST">
        <button class="btn btn-secondary"> Выйти </button>
    </form>
    <div>
        <button onclick="getOrders()" class="btn btn-secondary"> Мои заказы </button>
    </div>
    <div id="orders-box">

    </div>
</body>


<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous">

</script>

<script>
    function renderOrders(orders) {
        let $ordBox = $('#orders-box');
        for (let i = 0; i < orders.length(); i++) {
            $ordBox.prepend(orders[i]['price'] + ' ');
            $ordBox.prepend(orders[i]['isOrderShipped'] + ' ');
        }
    }
    function renderText() {
        $('#orders-box').prepend("Заказов нет.")
    }
    function getOrders() {
        $.ajax({
            type: "post",
            url: "/profile?action=order",
            success: function (response) {
                renderOrders(response)
            },
            error: function () {
                renderText();
            },
            dataType: "json"
        })
    }
</script>

<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous">
</script>

</html>
