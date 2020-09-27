package ru.itis.servlets;

import dao.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/users")
public class Servlet extends HttpServlet {

    private List<User> users;

    @Override
    public void init() {
        this.users = new ArrayList<>();

        User user = User.builder()
                .id(1L)
                .firstName("Damir")
                .lastName("Shagiev")
                .age(18).build();

        User user1 = User.builder()
                .id(2L)
                .firstName("Danil")
                .lastName("Asulgaraev")
                .age(19).build();

        User user2 = User.builder()
                .id(3L)
                .firstName("Rinat")
                .lastName("Nurmukhametov")
                .age(19).build();

        users.add(user);
        users.add(user1);
        users.add(user2);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("usersForJsp", users);
        request.getRequestDispatcher("WEB-INF/jsp/users.jsp").forward(request, response);

    }



}

