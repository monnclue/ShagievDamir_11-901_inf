<%@ page import="ru.itis.models.Product" %>
<%@ page import="ru.itis.jspFillers.JSPFiller" %>
<%@ page import="ru.itis.jspFillers.JSPFiller" %>
<%@ page import="ru.itis.services.AdminServiceImpl" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.itis.jspFillers.JSPFiller" %><%--
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
    <link rel="stylesheet" type="text/css" href="../../css/product.css"/>
    <link rel="stylesheet" type="text/css" href="../../css/night.css">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100&display=swap" rel="stylesheet">

    <title>     <%=product.getName()%>
    </title>
    <script
            src="https://code.jquery.com/jquery-3.5.1.js"
            integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
            crossorigin="anonymous"></script>
</head>
<body>
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

<%
    JSPFiller JSPFiller = new JSPFiller(request);
    JSPFiller.isAuthenticated();
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

<div>
    <img class="imageBorder" src="<%=product.getImageURL()%>" alt="<%=product.getName()%>">
</div>
<div class="align-text-product">
    <div id="name" class="name-fontSize" >
        <b><%=product.getName()%></b>
    </div>
    <div id="description">
        <%=product.getDescription()%>
    </div>
    <div id="price">
        Цена: <%=product.getPrice()%>р.
    </div>
        Выбрать размер:
    <div>
        <%List<String> sizes = (List<String>) request.getAttribute("sizes");
        %>
        <%
            String action = "/signUp";
            String method = "get";
            if (JSPFiller.isAuthenticated()) {
                action = "/product?action=save";
                method = "post";
            }
        %>

        <form action="<%=action%>" method="<%=method%>">
            <select class="custom-select select-size-product"  multiple size="3" name="size">
                <%if (sizes.contains("XS")) {%>
                <option value="XS">XS</option>
                <%
                    }
                %>
                <%if (sizes.contains("XS")) {%>
                <option value="XS">XS</option>
                <%
                    }
                %>
                <%if (sizes.contains("S")) {%>
                <option value="S">S</option>
                <%
                    }
                %>
                <%if (sizes.contains("M")) {%>
                <option value="M">M</option>
                <%
                    }
                %>
                <%if (sizes.contains("L")) {%>
                <option value="L">L</option>
                <%
                    }
                %>
                <%if (sizes.contains("XL")) {%>
                <option value="XL">XL</option>
                <%
                    }
                %>
            </select>
            <button type="submit" class="btn btn-outline-dark">Добавить в корзину</button>
        </form>
    </div>
</div>




</div>
<script>
    function ifAuthenticated(flag) {
        if (!flag) {
            document.getElementById("profile-button").remove();
            document.getElementById("cart-button").remove();
            document.getElementById("burger").remove();
        }
    }
    ifAuthenticated(<%=JSPFiller.isAuthenticated()%>)
</script>

<script>
    <%boolean flag = (sizes.size() == 0);%>
    let emptySizeList = <%=flag%>;
    function soldOut() {
        if (emptySizeList) document.getElementsByTagName("body").item(0).innerHTML = '<h1>SOLDOUT</h1>';
    }
    soldOut();
</script>

<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous">
</script>



</body>
</html>
