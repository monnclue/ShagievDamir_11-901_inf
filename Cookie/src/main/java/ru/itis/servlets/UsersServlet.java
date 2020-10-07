package ru.itis.servlets;

import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;
import ru.itis.repositories.UsersRepositoryJdbcImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 23.09.2020
 * 04. Html Servlet
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@WebServlet("/users")
public class UsersServlet extends HttpServlet {

    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "qwerty007";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/11-901";

//    private List<User> users;

    private UsersRepository usersRepository;
    
    @Override
    public void init() {
        try {
            Connection connection =
                    DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            usersRepository = new UsersRepositoryJdbcImpl(connection);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
//        this.users = new ArrayList<>();
//
//        User user = User.builder()
//                .id(1L)
//                .firstName("Misha")
//                .lastName("Khovaev")
//                .age(19)
//                .build();
//
//        User user1 = User.builder()
//                .id(2L)
//                .firstName("Artyom")
//                .lastName("Kondratyev")
//                .age(19)
//                .build();
//
//        User user2 = User.builder()
//                .id(3L)
//                .firstName("Marsel")
//                .lastName("Sidikov")
//                .age(26)
//                .build();
//
//        users.add(user);
//        users.add(user1);
//        users.add(user2);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        PrintWriter writer = response.getWriter();
//
//        writer.println("<table>");
//        writer.println("    <tr>");
//        writer.println("        <th>ID</th>");
//        writer.println("        <th>First Name</th>");
//        writer.println("        <th>Last Name</th>");
//        writer.println("        <th>Age</th>");
//        writer.println("    </tr>");
//
//        for (int i = 0; i < users.size(); i++) {
//            writer.println("<tr>");
//            writer.println("    <td>" + users.get(i).getId() + "</td>");
//            writer.println("    <td>" + users.get(i).getFirstName() + "</td>");
//            writer.println("    <td>" + users.get(i).getLastName() + "</td>");
//            writer.println("    <td>" + users.get(i).getAge() + "</td>");
//            writer.println("</tr>");
//        }
//
//        writer.println("</table>");
//
//        response.setContentType("text/html");

        String ageValue = request.getParameter("age");
        List<User> users = null;
        if (ageValue != null) {
            Integer age = Integer.parseInt(ageValue);
            users = usersRepository.findAllByAge(age);
        } else {
            users = usersRepository.findAll();
        }
        request.setAttribute("usersForJsp", users);
        request.getRequestDispatcher("WEB-INF/jsp/users.jsp").forward(request, response);
    }
}
