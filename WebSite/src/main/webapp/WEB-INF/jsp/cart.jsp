<%@ page import="ru.itis.models.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.itis.models.ProductForCart" %>
<%@ page import="ru.itis.jspFillers.JSPFiller" %>
<%@ page import="ru.itis.jspFillers.JSPFiller" %>
<%@ page import="ru.itis.models.Address" %>
<%@ page import="java.util.Arrays" %><%--
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
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Roboto:wght@100&display=swap');
    </style>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="../../css/sty.css"/>
    <link rel="stylesheet" type="text/css" href="../../css/night.css"/>
    <link rel="stylesheet" type="text/css" href="../../css/cart.css"/>

    <script
            src="https://code.jquery.com/jquery-3.5.1.js"
            integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
            crossorigin="anonymous"></script>
</head>
<body class="body-filter">
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


    <%
        JSPFiller JSPFiller = new JSPFiller(request);
        JSPFiller.isAuthenticated();
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



        </div>
    </nav>


    <div class="box center" id="productList">
        <table id="cartTable" class="table-border-radius align-table">
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
                    <div class="filters__cart_td">
                        <div class="name-fontSize"><b><%=product.getName()%></b></div>
                        <div>Размер: <b><%=product.getSize()%>
                        </b></div>
                        <div><%=product.getPrice()%>р.</div>
                    </div>
                </td>
                <td>
                    <form id="deleteFromCartID<%=product.getProductSize_id()%>" class="form-inline my-2 my-lg-0 button__delete" action="/cart?action=delete&prod=<%=product.getProductSize_id()%>" method="post">
                        <button class="btn btn-outline-dark my-2 my-sm-0" type="submit">Удалить из корзины</button>
                    </form>
                </td>
            </tr>

            <%
                }
            %>
        </table>
    </div>

<div id="right-block" class="address-block center">
    <div id="addresses-box" class="align-input_cart">
        <h3 class="text-10left"><b>Адрес</b></h3>
        <div class="form-group" onfocusout="hideAddresses()" >
            <label id="addresses-label" onmouseover="showAddresses()" class="text-10left" for="addresses">Показать сохраненные адреса</label>
            <div id="select-box">

            </div>

        </div>

        <form action="/cart?action=address" class="form-promo" method="post">
            <div class="form-group">
                <label class="red text-10left" for="nameInput">Имя</label>
                <input class="form-control select-size" id="nameInput" placeholder=" ">
                <label class="red text-10left" for="surnameInput">Фамилия</label>
                <input class="form-control select-size" id="surnameInput" placeholder=" ">
                <label class="red text-10left" for="countryInput">Страна</label>
                <input class="form-control select-size" id="countryInput" placeholder=" ">
                <label class="red text-10left" for="cityInput">Город</label>
                <input class="form-control select-size" id="cityInput" placeholder=" ">
                <label class="red text-10left" for="addressInput">Адрес</label>
                <input class="form-control select-size" id="addressInput" placeholder=" ">
                <label class="red text-10left" for="postCodeInput">Почтовый код</label>
                <input class="form-control select-size" id="postCodeInput" placeholder=" ">
                <label class="red text-10left" for="phoneNumInput">Номер телефона</label>
                <input class="form-control select-size" id="phoneNumInput" placeholder=" ">
            </div>
        </form>



        <div onclick="saveAddress()" class="align-button-addAddress pointer_cursor" >
            <button class="btn btn-outline-dark button-address">Добавить в сохраненные</button>
        </div>
        <br>


    </div>


    <div class="align-order-box">
        <div class="form-group">
            <label class="text-10left" for="shipping"><h3><b>Вариант доставки</b></h3></label>
            <select size="2" onchange="setShip()" class="form-control select-size" id="shipping">
                <option value="kazan">Доставка по г.Казань – 0р.</option>
                <option value="pochta">Почта России – 300р.</option>
            </select>
        </div>
        <div class="text-10left" id="priceField">
            <h3> <b> К оплате: <%=price%> р.</b></h3>
        </div>
        <div class="form-promo">
            <div class="form-group">
                <label class="red text-10left" id="promolabel" for="promo">  Промокод</label>
                <input class="form-control uppercase input-promo" id="promo" placeholder=" ">
                <button class="btn btn-outline-dark button-position" id="submitPromo">Применить</button>
            </div>
        </div>
        <div>
            <button id="orderForCart-button" onclick="postOrder()" class="btn btn-outline-dark  button-position_order">Оформить заказ</button>
        </div>


    </div>
</div>












    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous">
    </script>



    <script>
        let shipPrice = 0;
        let promoPrice = 0;

        function getPrice() {
            let totalPrice = (shipPrice + <%=price%> - promoPrice);
            document.getElementById("priceField").innerHTML = '<h3> <b> К оплате: ' + totalPrice + ' р. </h3> </b>';
        }


        function editShipPrice(value) {
            shipPrice = value;
        }

        function editPromoPrice(value) {
            promoPrice = value;
        }
    </script>
    <script>

        function setShip() {
            let ship_price = 0;
            let $shipMethod = $('#shipping').val();
            let data = {"shippingMethod": $shipMethod};
            $.ajax({
                type: "POST",
                url: "/cart?action=ship",
                data: JSON.stringify(data),
                dataType: "json",
                contentType: "application/json"
            })
            if ($shipMethod === 'pochta') {
                ship_price = 300;
                editShipPrice(ship_price);
            }
            if ($shipMethod === 'kazan') {
                if (ship_price === 300) {
                    editShipPrice(-300)
                } else {
                    editShipPrice(0)
                }
            }
            getPrice();

        }

    </script>


    <script>

        <%
            List<Address> addresses = (List<Address>) request.getAttribute("addresses");
        %>
        let addressesSave = [];

        <%
        for (int i = 0; i < addresses.size(); i++) {
        %>
        let addr<%=i%> = {
            "id": <%=addresses.get(i).getId()%>,
            "firstName": '<%=addresses.get(i).getFirstName()%>',
            "lastName": '<%=addresses.get(i).getLastName()%>',
            "country": '<%=addresses.get(i).getCountry()%>',
            "city": '<%=addresses.get(i).getCity()%>',
            "street": '<%=addresses.get(i).getStreet()%>',
            "postcode": '<%=addresses.get(i).getPostcode()%>',
            "phone": '<%=addresses.get(i).getPhone()%>'
        }
        addressesSave[<%=i%>] = addr<%=i%>;
        <%
        }
        %>
        function showAddresses() {
            document.getElementById('select-box').innerHTML = '<select size="2" onchange="setAddress()" class="form-control select-size" id="addresses">\n' +
                <%
                if (addresses.size() == 0) {
                %>
                '  <option value="none">Нет сохраненных адресов</option>\n' +
                <%
                } else {
                    for (Address address: addresses) {
                        %>
                '  <option value="<%=address.getId()%>"><%=address.getCountry()%> <%=address.getCity()%> <%=address.getStreet()%> <%=address.getPostcode()%></option>\n' +
                <%
            }
        }
        %>
                '            </select>'
            document.getElementById('addresses-label').innerText = '';
            document.getElementById('addresses').focus();
        }
        function hideAddresses() {
            document.getElementById('addresses-label').innerText = 'Показать сохраненные адреса';
            document.getElementById('addresses').remove();
        }


        function renderAddressesWindow(addresses) {
            addressesSave = addresses;
            let innerHtml = '';
            for (let i = 0; i < addresses.length; i++) {
                innerHtml +=
                    '<option value="'+ addresses[i]['id']  + '">' +
                    addresses[i]['country'] + ' ' +
                    addresses[i]['city'] + ' ' +
                    addresses[i]['street'] + ' ' +
                    addresses[i]['postcode'] + ' ' +


                    '</option>';
            }
            $('#addresses').html(innerHtml);
        }


        function saveAddress() {
            let data = {
                "firstName": $('#nameInput').val(),
                "lastName": $('#surnameInput').val(),
                "country": $('#countryInput').val(),
                "city": $('#cityInput').val(),
                "street": $('#addressInput').val(),
                "postcode": $('#postCodeInput').val(),
                "phone": $('#phoneNumInput').val()
            }

            $.ajax({
                type: "POST",
                url: "/cart?action=address",
                data: JSON.stringify(data),
                success: function (response) {
                    renderAddressesWindow(response);
                },
                dataType: "json",
                contentType: "application/json"
            })

        }

        function setAddress() {
            for (let i = 0; i < addressesSave.length; i++) {
                if (addressesSave[i]['id'] == $('#addresses').val()) {
                    $('#nameInput').val(addressesSave[i]['firstName']);
                    $('#surnameInput').val(addressesSave[i]['lastName']);
                    $('#countryInput').val(addressesSave[i]['country']);
                    $('#cityInput').val(addressesSave[i]['city']);
                    $('#addressInput').val(addressesSave[i]['street']);
                    $('#postCodeInput').val(addressesSave[i]['postcode']);
                    $('#phoneNumInput').val(addressesSave[i]['phone']);
                }
            }
        }

    </script>


    <script>
        let $promo = $('#promo');
        let codeValid;
        $('#submitPromo').on("click", function () {
            $.ajax({
                type: "POST",
                url: "/cart?action=promo",
                data: JSON.stringify({"code": $promo.val()}),
                success: function (response) {
                    if (response === "notfound") {
                        $('#not-found-promo').remove();
                        $('#found-promo').remove();
                        $('#promolabel')
                            .after("<div id='not-found-promo' " +
                                "style='color: red'>Введён несуществующий промокод.<div>")
                    } else if (response.includes("exist")) {
                        $('#found-promo').remove();
                        $('#not-found-promo').remove();
                        $('#submitPromo').remove();
                        $('#promo').remove();
                        $('#promolabel')
                            .after("<div id='found-promo' " +
                                "style='color: lawngreen'>Промокод применён.<div>");
                        editPromoPrice(response.split(' ')[1]);
                        getPrice();
                    }
                },
                dataType: "text",
                contentType: "application/json"
            })
        })
    </script>

    <script>

        function postOrder() {
        let data = {
                "firstName": $('#nameInput').val(),
                "lastName": $('#surnameInput').val(),
                "country": $('#countryInput').val(),
                "city": $('#cityInput').val(),
                "street": $('#addressInput').val(),
                "postcode": $('#postCodeInput').val(),
                "phone": $('#phoneNumInput').val()
        }
        $.ajax({
            type: "POST",
            url: "/cart?action=order",
            data: JSON.stringify(data),
            success: function (response) {
                console.log(response);
                window.open("/order");
            },
            dataType: "text",
            contentType: "application/json"
        })
    }

</script>

</body>
</html>
