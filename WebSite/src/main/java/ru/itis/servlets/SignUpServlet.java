package ru.itis.servlets;

import ru.itis.dto.UserDto;
import ru.itis.dto.UserForm;
import ru.itis.services.CheckingService;
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
 * 26.10.2020
 * 05. Skeleton
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {

    private SignUpService signUpService;
    private SignInService signInService;
    private CheckingService checkingService;
    private HttpSession session;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.signUpService = (SignUpService) context.getAttribute("signUpService");
        this.signInService = (SignInService) context.getAttribute("signInService");
        this.checkingService = (CheckingService) context.getAttribute(
                "checkingService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/sign_up.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        UserForm form = UserForm.builder()
                .email(req.getParameter("email"))
                .firstName(req.getParameter("firstName"))
                .lastName(req.getParameter("lastName"))
                .password(req.getParameter("password"))
                .build();

        String message = checkingService.incorrectInput(form);

        System.out.println(message);
        if (message != null) {
            HttpSession session = req.getSession();
            session.setAttribute("message", message);
            resp.sendRedirect("/signUp");
        } else {
            signUpService.signUp(form);
            UserDto userDto = signInService.signIn(form);
            if (userDto != null) {
                // взяли существующую или сделали новую
                HttpSession session = req.getSession();
                session.setAttribute("user", userDto);
                resp.sendRedirect("/success");
            } else {
                resp.sendRedirect("/signUp");
            }
        }
    }
}
