<%@ page import="java.util.Arrays" %>
<%@ page import="ru.itis.models.Order" %><%--
  Created by IntelliJ IDEA.
  User: kellyss
  Date: 22/11/2020
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Заказ</title>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Roboto:wght@100&display=swap');
    </style>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="../../css/sty.css"/>
    <link rel="stylesheet" type="text/css" href="../../css/night.css"/>
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
Ваш заказ успешно сформирован.
<%Order order = (Order) request.getAttribute("order");%>
Стоимость: <%=order.getPrice()%>

Оплатить ............................??
???
???
</body>
</html>
