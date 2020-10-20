package ru.itis.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;
import ru.itis.repositories.UsersRepositoryJdbcImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet ("/ajaxsearch")
public class AjaxSearchServlet extends HttpServlet {

    public static final String DB_USERNAME = "postgres";
    public static final String DB_PASSWORD = "ишыщкщ";
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private Connection connection;
    private UsersRepositoryJdbcImpl usersRepository;
    private List<User> users = new ArrayList<>();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void init() throws ServletException {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            usersRepository = new UsersRepositoryJdbcImpl(connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/html/ajax_search.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = objectMapper.readValue(req.getReader(), User.class);
        String usersAsJson = objectMapper
                .writeValueAsString(usersRepository.findAllByName(user.getFirstName()));
        resp.setContentType("application/json");
        resp.getWriter().println(usersAsJson);
    }
}
