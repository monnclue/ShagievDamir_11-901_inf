package ru.itis.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.itis.models.ProductForCart;
import ru.itis.services.CartService;
import ru.itis.services.ProductService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    private ProductService productService;
    private CartService cartService;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.productService = (ProductService) context.getAttribute("productService");
        this.cartService = (CartService) context.getAttribute("cartService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProductForCart> products = cartService.getChosen(req.getSession());
//        req.setCharacterEncoding("UTF-8");
  //      resp.setCharacterEncoding("UTF-8");
        req.setAttribute("products", products);
        req.getRequestDispatcher("/WEB-INF/jsp/cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
