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
    <link rel="stylesheet" type="text/css" href="../../css/night.css"/>
    <link rel="stylesheet" type="text/css" href="../../css/cart.css"/>

    <script
            src="https://code.jquery.com/jquery-3.5.1.js"
            integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
            crossorigin="anonymous"></script>
</head>
<body>

    <%
        NavbarJSPFiller navbarJSPFiller = new NavbarJSPFiller(request);
        navbarJSPFiller.isAuthenticated();
    %>

    <div id="search-windowId" class="color-search-window content-search-window ">
    </div>


    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/shop">AWESOME SHOP</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" id="profile-button" href="/profile">Мой профиль<span class="sr-only">(current)</span></a>
                </li>
            </ul>
            <form id="foc" class="my-2 my-lg-0">
                <input onclick="sendReq($('#search-input-field').val())" onkeyup="sendReq($('#search-input-field').val())"
                       class="form-control form-control-lg align-search-box" id="search-input-field"
                       type="search" placeholder="Search" aria-label="Search">
            </form>
            <a class="nav-link pointer_cursor" id="switch-mode">
                <img class="moonSize" src="../../images/night-mode_icon.svg" alt="change mode">
            </a>


        </div>
    </nav>


    <div class="box" id="productList">
        <table class="table-border-radius">
            <%
                int price = 0;
                List<ProductForCart> products = (List<ProductForCart>) request.getAttribute("products");
                for (ProductForCart product : products) {
                    price += product.getPrice();
            %>
            <tr id="catTrId" class="cart-products-box">
                <td>
                    <div class="filters__img_small imageBorder"><img class="imageBorder" src="<%=product.getImageURL()%>" alt="<%=product.getName()%>">
                    </div>
                </td>
                <td>
                    <div class="filters__cart">
                        <div class="name-fontSize"><b><%=product.getName()%></b></div>
                        <div>Размер: <b><%=product.getSize()%>
                        </b></div>
                        <div><%=product.getPrice()%>р.</div>
                    </div>
                </td>
                <td>
                    <form id="deleteFromCartID" class="form-inline my-2 my-lg-0 button__delete" >
                        <button class="btn btn-outline-dark my-2 my-sm-0" type="submit">Удалить из корзины</button>
                    </form>
                </td>
            </tr>

            <%
                }
            %>
        </table>
    </div>




    <form action="/promo" class="form-promo" method="post">
        <div class="form-group">
        <label class="red text-10left" for="exampleFormControlInput1">  Промокод</label>
        <input class="form-control uppercase input-promo" id="exampleFormControlInput1" placeholder=" ">
        <button type="submit" class="btn btn-outline-dark button-position">Применить</button>
        </div>
    </form>

    <div class="align-order-box">
        <div class="text-10left">
            <b>К оплате:  </b> <%=price%> р.
        </div>
        <form action="/order" method="POST">
            <button class="btn btn-outline-dark  button-position_order">Оформить заказ</button>
        </form>
    </div>











    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous">
    </script>

    <script>
        <%
            boolean night;
            Boolean mode = (Boolean) request.getSession().getAttribute("night-mode");
            if( mode == null)  {
                night = false;
            } else {
                night = mode;
            }
        %>
        let night = <%=night%>;
        console.log(night);

        function toggleMode() {
            let body = document.getElementsByTagName("body").item(0);
            if (night) body.classList.add('dark-mode');
            else body.classList.remove('dark-mode');

        }

        toggleMode();

        $('#switch-mode')
            .click(function () {
                document.getElementsByTagName("body").item(0).classList.toggle('dark-mode');
                night = !night;
                changeMode();
            })

        function changeMode() {

            let data = {
                "night" : night
            }
            $.ajax({
                type: "POST",
                url: "/mode",
                data: JSON.stringify(data),
                success: function (response) {
                    night = !response;
                },
                dataType: "json",
                contentType: "application/json"
            });

        }
    </script>

    <script>
        function clearSearchWindow() {
            document.getElementById("search-windowId").innerHTML = '';
        }

        function renderSearchWindow() {
            clearSearchWindow(products);
            if (products != null) {
                let searchWindow = document.getElementById("search-windowId");
                let br = '<br>';
                let div = '<div class="art-products-box">';
                let divClose = '</div>';

                for (let i = 0; i < products.length; i++) {
                    console.log(products[i]['name'])
                    searchWindow.insertAdjacentHTML('afterbegin',divClose);
                    searchWindow.insertAdjacentHTML('afterbegin',br);
                    searchWindow.insertAdjacentText('afterbegin',products[i]['description']);
                    searchWindow.insertAdjacentText('afterbegin','. ');
                    searchWindow.insertAdjacentText('afterbegin',products[i]['name']);
                    searchWindow.insertAdjacentHTML('afterbegin',div)
                }
            }



        }

        function sendReq(name) {

            let data = {
                "name": name
            };

            $.ajax({
                type: "POST",
                url: "/shop?action=name",
                data: JSON.stringify(data),
                success: function (response) {
                    renderSearchWindow(response);
                },
                dataType: "json",
                contentType: "application/json"
            });
        }
    </script>
</body>
</html>
