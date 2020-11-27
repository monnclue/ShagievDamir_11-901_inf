package ru.itis.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.itis.services.AddressService;
import ru.itis.services.CartService;
import ru.itis.services.ProductService;
import ru.itis.services.ProfileService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 28.10.2020
 * 05. Skeleton
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();
    private ProfileService profileService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.profileService = (ProfileService) context.getAttribute("profileService");
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("action").equals("order")) {
            String ordersAsJson = objectMapper.writeValueAsString(
                    profileService.getOrders(req.getSession()));
            resp.getWriter().print(ordersAsJson);
        }
    }
}
