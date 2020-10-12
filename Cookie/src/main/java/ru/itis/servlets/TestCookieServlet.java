package ru.itis.servlets;

import ru.itis.models.UserForCookieTask;
import ru.itis.repositories.CookieServletTemplate;
import ru.itis.repositories.UsersCookieTaskImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/account")
public class TestCookieServlet extends HttpServlet {

    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "ишыщкщ";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cookie[] cookies = req.getCookies();
        req.setAttribute("user", getUser(cookies));
        req.getRequestDispatcher("WEB-INF/jsp/testCookie.jsp")
                .forward(req, resp);

    }

    private UserForCookieTask getUser(Cookie[] cookies) {

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        UsersCookieTaskImpl usersCookieTask = new UsersCookieTaskImpl(connection);
        CookieServletTemplate cookieServletTemplate = new CookieServletTemplate(cookies);
        String uuid = cookieServletTemplate.getValueByCookieName("myCookie");
        List<UserForCookieTask> user = usersCookieTask.findByUUID(uuid);
        return user.get(0);
    }

}
