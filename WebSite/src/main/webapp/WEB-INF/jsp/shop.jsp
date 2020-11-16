<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ru.itis.jspFillers.NavbarJSPFiller" %>
<%@ page import="ru.itis.models.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.itis.jspFillers.NavbarJSPFiller" %><%--
  Created by IntelliJ IDEA.
  User: kellyss
  Date: 02/11/2020
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Shop</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="../../css/shop.css"/>
    <link rel="stylesheet" type="text/css" href="../../css/sty.css"/>
    <link rel="stylesheet" type="text/css" href="../../css/search.css"/>
    <link rel="stylesheet" type="text/css" href="../../css/night.css"/>

    <script
            src="https://code.jquery.com/jquery-3.5.1.js"
            integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
            crossorigin="anonymous"></script>


</head>
<body class="dark-mode" id="body">
<div id="search-windowId" class="color-search-window content-search-window ">

</div>
<%
    NavbarJSPFiller navbarJSPFiller = new NavbarJSPFiller(request);
%>
<div id="allWoSearch">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/shop">AWESOME SHOP</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Категории
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" onclick="sendReqCat('T-Shirt')" href="#">Футболки</a>
                        <a class="dropdown-item" onclick="sendReqCat('Sweater')" href="#">Свитера</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" onclick="sendReqCat('Pants')" href="#">Штаны</a>
                    </div>
                </li>
                <li class="nav-item">
                    <a class="nav-link" id="profile-button" href="/profile">Мой профиль<span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item" id="cart-button">
                    <a class="nav-link" href="/cart">Корзина</a>
                </li>
                <li class="nav-item" id="signUp-button">
                    <a class="nav-link" href="/signUp">Войти</a>
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




    <table id="categories_box">

    </table>

</div>








<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous">

</script>

<script>
    function clearSearchWindow(products) {
        document.getElementById("search-windowId").innerHTML = '';
    }
    function renderCat(products, table) {
        let innerHtml = '';
        let flag = false;

        for (let i = 0; i < products.length; i++) {
            let tr = '';
            let trClose = '';
            if(flag) {
                trClose = '</tr>';
            } else {
                tr = '<tr>';
            }
            flag = !flag;

            innerHtml += tr +
                '      <td class="pointer_cursor link" ><a href="/product?id='
                + products[i]['id'] +'">\n' +
                '                <div class="filters__img imageBorder">' +
                '                <img class="imageBorder" src="'
                + products[i]['imageURL'] +
                                '" alt="' + products[i]['name'] + '">\n' +
                '                </div>\n' +
                '           </a> </td>\n' +
                '            <td>\n' +
                '                <div class="filters__img">\n' +
                '                    <div class="name-fontSize"><b>' + products[i]['name'] + '</b></div>\n' +
                '                    <div>' + products[i]['description'] + '</div><br>\n' +
                '                    <div>' + products[i]['price'] + 'р.</div>\n' +
                '                </div>\n' +
                '            </td>\n' +
                '' + trClose;

        }
        table.html(innerHtml);
    }

    function renderSearchWindow(products) {
        clearSearchWindow(products);
        if (products != null) {
            let searchWindow = document.getElementById("search-windowId");
            let br = '<br>';

            for (let i = 0; i < products.length; i++) {
                console.log(products[i]['name'])
                searchWindow.insertAdjacentHTML('afterbegin',br);
                searchWindow.insertAdjacentText('afterbegin',products[i]['description']);
                searchWindow.insertAdjacentText('afterbegin','. ');
                searchWindow.insertAdjacentText('afterbegin',products[i]['name']);
            }
        }



    }


    function sendReqCat(category) {
        let data = {
            "type": category
        };

        $.ajax({
            type: "POST",
            url: "/shop?action=cat",
            data: JSON.stringify(data),
            success: function (response) {
                renderCat(response, $('#categories_box'));
            },
            dataType: "json",
            contentType: "application/json"
        });
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

<script>
    function clear(table) {
        let clearHtml = '';
        table.html(clearHtml);
    }
</script>

<script>
    function ifAuthenticated(flag) {
        if (!flag) {
            document.getElementById("profile-button").remove();
            document.getElementById("cart-button").remove();
        } else {
            document.getElementById("signUp-button").remove();
        }
    }
    ifAuthenticated(<%=navbarJSPFiller.isAuthenticated()%>)
</script>

<script>
    function delNameOfSelect() {
        let sel = document.getElementById("nameCat");
        sel.remove();
    }
</script>

<script>
    $('#search-input-field')
    .focusin(function () {
        document.getElementById("search-windowId").style.maxHeight = 80 + 'px';
    })
    .focusout(function () {
        document.getElementById("search-windowId").style.maxHeight = 0 + 'px';
    });
</script>

<script>
    <%
        boolean night;
        Boolean mode = (Boolean) request.getSession().getAttribute("night-mode");
        if( mode == null)  {
            System.out.println("there");
            night = false;
        } else {
            System.out.println("th");
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



<!-- вдруг понадобится -->
<%  //String[] events = new String[] {"keyup","focusin", "focusout"};
    //for (int i = 0; i < 2; i++) {%>


</body>
</html>
