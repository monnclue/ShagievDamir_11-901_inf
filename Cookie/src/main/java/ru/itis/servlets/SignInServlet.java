package ru.itis.servlets;

/**
 * 05.10.2020
 * 04. Html Servlet
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */

import ru.itis.models.User;
import ru.itis.models.UserForCookieTask;
import ru.itis.repositories.LoginTemplate;
import ru.itis.repositories.UsersCookieTaskImpl;
import ru.itis.repositories.UsersCookieTaskRepository;
import ru.itis.repositories.UsersRepositoryJdbcImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 * На GET-запрос предоставлять страницу для входа
 * На пост запрос получает login и password, ищет в базе (с помощью Repository)
 * если такой пользователь с таким логином и паролем есть в базе
 * то отдать ему cookie с UUID (этот UUID также нужно запомнить в базе)
 */

@WebServlet("/signin")
public class SignInServlet extends HttpServlet {

    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "ишыщкщ";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/jsp/signin.jsp")
                .forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Cookie[] cookies = req.getCookies();
        Cookie mycookie = null;

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("myCookie")) {
                mycookie = cookie;
            }
        }

        try {
            if (mycookie == null) {
                Connection connection =
                        DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                LoginTemplate loginTemplate = new LoginTemplate(connection);
                resp.addCookie(loginTemplate.getCookie(req.getParameter("login"),
                        req.getParameter("password")));
            }
            resp.sendRedirect("/account");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}

