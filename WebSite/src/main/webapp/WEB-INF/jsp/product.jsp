<%@ page import="ru.itis.models.Product" %>
<%@ page import="ru.itis.jspFillers.NavbarJSPFiller" %>
<%@ page import="ru.itis.jspFillers.NavbarJSPFiller" %><%--
  Created by IntelliJ IDEA.
  User: kellyss
  Date: 12/11/2020
  Time: 19:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<% Product product = (Product) request.getAttribute("product");%>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="../../css/sty.css"/>
    <title>     <%=product.getName()%>
    </title>
</head>
<body>

<%
    NavbarJSPFiller navbarJSPFiller = new NavbarJSPFiller(request);
    navbarJSPFiller.isAuthenticated();
%>


<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/shop">AWESOME SHOP</a>
    <button  id="burger" class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item ">
                <a class="nav-link" id="profile-button" href="/profile">Мой профиль<span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" id="cart-button" href="/cart">Корзина</a>
            </li>
        </ul>
    </div>

</nav>

<div id="image-box" class="filters__img_big imageBorder">
    <img class="imageBorder" src="<%=product.getImageURL()%>" alt="<%=product.getName()%>">
</div>
<div id="name" >
    <%=product.getName()%>
</div>
<script>
    function ifAuthenticated(flag) {
        if (!flag) {
            document.getElementById("profile-button").remove();
            document.getElementById("cart-button").remove();
            document.getElementById("burger").remove();
        }
    }
    ifAuthenticated(<%=navbarJSPFiller.isAuthenticated()%>)
</script>


</body>
</html>
