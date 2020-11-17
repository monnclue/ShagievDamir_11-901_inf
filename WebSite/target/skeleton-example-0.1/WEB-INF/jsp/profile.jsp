<%--
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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="../../css/sty.css"/>
    <link rel="stylesheet" type="text/css" href="../../css/night.css">

    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous">
    </script>
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

        <a class="nav-link pointer_cursor" id="switch-mode">
            <img class="moonSize" src="../../images/night-mode_icon.svg" alt="change mode">
        </a>

    </div>

</nav>

    <form action="/logout" method="POST">
        <button class="btn btn-secondary"> Выйти </button>
    </form>
    <form action="/order" method="get">
        <button class="btn btn-secondary"> Мои заказы </button>
    </form>
</body>


<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous">
</script>




</html>
