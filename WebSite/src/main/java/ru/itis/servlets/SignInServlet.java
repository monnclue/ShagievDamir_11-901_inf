package ru.itis.servlets;

import ru.itis.dto.UserDto;
import ru.itis.dto.UserForm;
import ru.itis.services.SignInService;
import ru.itis.services.SignUpService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 28.10.2020
 * 05. Skeleton
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {
    private SignInService signInService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.signInService = (SignInService) context.getAttribute("signInService");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/sign_in.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserForm form = UserForm.builder()
                .email(req.getParameter("email"))
                .password(req.getParameter("password"))
                .build();

        UserDto userDto = signInService.signIn(form);

        if (userDto != null) {
            // взяли существующую или сделали новую
            HttpSession session = req.getSession();
            if (userDto.getEmail().equals("admin@a.a")) {
                session.setAttribute("admin", userDto);
            }
            session.setAttribute("user", userDto);
            resp.sendRedirect("/shop");
        } else {
            resp.sendRedirect("/signIn");
        }
    }
}
