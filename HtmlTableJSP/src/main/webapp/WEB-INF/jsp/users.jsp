<%@ page import="java.sql.*" %><%--
  Created by IntelliJ IDEA.
  User: kellyss
  Date: 27/09/2020
  Time: 19:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>users</title>
</head>
<body>


<table>

    <tr>
        <th>email</th>
        <th>password</th>
    </tr>
    <%

    final String DB_USERNAME = "postgres";
    final String DB_PASSWORD = "ишыщкщ";
    final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    Connection connection = null;
    try {
        connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }

        Statement statement1 = connection.createStatement();
        ResultSet result = statement1.executeQuery("SELECT * FROM users;");

    %>

    <%
        while (result.next()) {
            %>
            <tr>
                <td>
                    <%=result.getString(1)%>
                </td>
                <td>
                    <%=result.getString(2)%>
                </td>
            </tr>
            <%
        }
    %>
</table>
</body>
</html>
