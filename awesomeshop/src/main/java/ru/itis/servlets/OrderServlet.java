package ru.itis.servlets;

import ru.itis.repositories.OrderRepository;
import ru.itis.services.AdminService;
import ru.itis.services.CartService;
import ru.itis.services.OrderService;
import ru.itis.services.ProductService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

    private OrderService orderService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.orderService = (OrderService) context.getAttribute("orderService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("order", orderService.getOrderForAccount(req.getSession()));
        // when payment will be
        // orderService.deleteNotPayed(req.getSession());
        req.getRequestDispatcher("/WEB-INF/jsp/order.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
