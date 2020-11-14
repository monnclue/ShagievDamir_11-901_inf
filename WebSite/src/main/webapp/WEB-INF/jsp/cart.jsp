<%@ page import="ru.itis.models.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.itis.models.ProductForCart" %>
<%@ page import="ru.itis.jspFillers.NavbarJSPFiller" %>
<%@ page import="ru.itis.jspFillers.NavbarJSPFiller" %><%--
  Created by IntelliJ IDEA.
  User: kellyss
  Date: 02/11/2020
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>Корзина</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="../../css/sty.css"/>


</head>
<body>

    <%
        NavbarJSPFiller navbarJSPFiller = new NavbarJSPFiller(request);
        navbarJSPFiller.isAuthenticated();
    %>


    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/shop">AWESOME SHOP</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item ">
                    <a class="nav-link" id="profile-button" href="/profile">Мой профиль<span class="sr-only">(current)</span></a>
                </li>
            </ul>
        </div>
    </nav>


    <div class="box" id="productList">
        <%
            int price = 0;
            List<ProductForCart> products = (List<ProductForCart>) request.getAttribute("products");
            for (ProductForCart product : products) {
                price += product.getPrice();
        %>
        <tr>
            <td>
                <div class="filters__img"><img src="<%=product.getImageURL()%>" alt="<%=product.getName()%>">
                </div>
            </td>
            <td>
                <div class="filters__img">
                    <div>"<%=product.getName()%>"</div>
                    <div>Размер: <b><%=product.getSize()%>
                    </b></div>
                    <div><%=product.getPrice()%>р.</div>
                </div>

            </td>
            <td>
                <form action="" method="post">
                    <br>
                    <button class="btn btn-secondary"> Убрать из корзины</button>
                </form>
            </td>
        </tr>
        <%
            }
        %>
    </div>


    <form action="/promo" method="post">
        <div class="form-group">
        <label class="red" for="exampleFormControlInput1">  Промокод</label>
        <input class="form-control uppercase" id="exampleFormControlInput1" placeholder=" ">
        <button type="submit" class="btn btn-secondary">Применить</button>
        </div>
    </form>

    <div>
        <h5>    К оплате: </h5>
        <%=price%> р.
    </div>




    <form action="/order" method="POST">
        <button class="btn btn-secondary">Оформить заказ</button>
    </form>


</body>
</html>
