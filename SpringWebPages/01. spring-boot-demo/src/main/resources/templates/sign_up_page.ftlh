<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Регистрация</title>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Roboto:wght@100&display=swap');
    </style>
    <link rel="stylesheet" type="text/css" href="../../css/night.css"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="../../css/sty.css"/>
    <link rel="stylesheet" type="text/css" href="../../css/signUp.css"/>
    <script src="/js/darkmode"></script>
</head>
<body>
<div id="signup-box">
    <form action="/signUp" method="post">
        <div class="form-group row">
            <label for="l1" class="col-sm-2 col-form-label">Email</label>
            <div class="col-sm-10">
                <input type="email" name="email" class="form-control" id="l1" placeholder="example@email.com">
            </div>
        </div>
        <div class="form-group row">
            <label for="l2"  class="col-sm-2 col-form-label">Имя</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="l2" name="firstName" placeholder="">
            </div>
        </div>
        <div class="form-group row">
            <label for="l3"  class="col-sm-2 col-form-label">Фамилия</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="l3" name="lastName" placeholder="">
            </div>
        </div>
        <div class="form-group row">
            <label for="l4" class="col-sm-2 col-form-label">Password</label>
            <div class="col-sm-10">
                <input type="password" autocomplete="off" name="password" class="form-control" id="l4" placeholder="">
            </div>
        </div>

        <button type="submit" class="btn btn-dark">Создать аккаунт</button>
    </form>
</div>

<form id="signin-form" action="/signIn" method="get">
    <button id="signin-button" style="" class="btn btn-dark"> Войти </button>
</form>

</body>
</html>