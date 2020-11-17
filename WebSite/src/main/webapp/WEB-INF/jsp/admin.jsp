<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="ru.itis.repositories.ProductsRepositoryJdbcTemplateImpl" %>
<%@ page import="ru.itis.services.ProductService" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.itis.models.Product" %>
<%@ page import="ru.itis.services.AdminService" %>
<%@ page import="ru.itis.models.ProductSize" %><%--
  Created by IntelliJ IDEA.
  User: kellyss
  Date: 02/11/2020
  Time: 20:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Admin</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="../../css/sty.css"/>
    <link rel="stylesheet" type="text/css" href="../../css/night.css"/>
    <style type="text/css">
        TABLE {
            width: 300px; /* Ширина таблицы */
            border-collapse: collapse; /* Убираем двойные линии между ячейками */
        }
        TD, TH {
            padding: 3px; /* Поля вокруг содержимого таблицы */
            border: 2px solid black; /* Параметры рамки */
        }
        TH {
            background: #C0C0C0; /* Цвет фона */
        }

        .imageBorder {
            border-radius: 90px;
        }
        .filters__img {
            width: 200px;
            height: 200px;
            overflow: hidden;
            margin: 10px;
        }

        .filters__img img {
            width: initial;
            height: 100%;
        }

        .box{
            display: flex;
        }

    </style>
</head>
<body>


<form action="/admin?action=input" method="post">
    <div class="form-group">
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <label class="input-group-text" for="nameId">Название</label>
            </div>
            <input class="form-control" name="name" id="nameId" placeholder=" ">
        </div>
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <label class="input-group-text" for="typeId">Тип</label>
            </div>
            <select class="custom-select" name="type" id="typeId">
                <option selected>Choose...</option>
                <option value="T-Shirt">Футболка</option>
                <option value="Sweater">Свитер</option>
                <option value="Pants">Штаны</option>
            </select>
        </div>
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <label class="input-group-text" for="imageURLId">URL фотографии</label>
            </div>
            <input class="form-control" name="imageURL" type="url" id="imageURLId" placeholder="">
        </div>
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <label class="input-group-text" for="descriptionId">Описание</label>
            </div>
            <input class="form-control" name="description" id="descriptionId" placeholder="">
        </div>
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <label class="input-group-text"  for="priceId">Стоимость (в рублях)</label>
            </div>
            <input class="form-control" name="price" type="number" id="priceId" placeholder="">
        </div>
        <button type="submit" class="btn btn-secondary">Добавить</button>
    </div>
</form>

<table>
    <tr>
        <th>image</th>
        <th>id</th>
        <th>name</th>
        <th>type</th>
        <th>description</th>
        <th>price</th>
        <th>sizes</th>
        <th>edit size count</th>
        <th>delete</th>
    </tr>

    <div class="box" id="productList">
        <%
            List<Product> products = (List<Product>) request.getAttribute("products");
            for (int i = 0; i < products.size(); i++) {
        %>
        <tr>
            <td>
                <div class="filters__img"><img src="<%=products.get(i).getImageURL()%>" alt="<%=products.get(i).getName()%>">
                </div>
            </td>
            <td>
                <%=products.get(i).getId()%>
            </td>
            <td>
                <%=products.get(i).getName()%>
            </td>
            <td>
                <%=products.get(i).getType()%>
            </td>
            <td>
                <%=products.get(i).getDescription()%>
            </td>
            <td>
                <%=products.get(i).getPrice()%>р.
            </td>
            <td>
                <%AdminService adminService = (AdminService) request.getAttribute("admin");
                    List<ProductSize> productSizes = adminService.getProductsSizes(products.get(i));
                    for (ProductSize productSize : productSizes) {
                %>
                    <%=productSize.getSize()%> : <%=productSize.getCount()%>
                <%
                    }
                %>

            </td>
            <td>
                <form action="/admin?action=addsize&id=<%=products.get(i).getId()%>" method="post">
                    <p>Добавить размер</p>
                    <label>
                        <input name="count" type="number">
                        <select multiple size="4" name="size">
                            <option value="XS">XS</option>
                            <option value="S">S</option>
                            <option value="M">M</option>
                            <option value="L">L</option>
                            <option value="XL">XL</option>
                        </select>
                        <button type="submit" class="btn btn-secondary"> Ок </button>
                    </label>
                </form>
            </td>
            <td>
                <form action="/admin?action=delete?id=<%=products.get(i).getId()%>" method="post">
                    <button class="btn btn-secondary"> Удалить </button>
                </form>
            </td>
        </tr>
        <%
            }
        %>

    </div>




</table>

</body>
</html>
