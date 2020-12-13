package ru.itis.servlets;

import ru.itis.models.Product;
import ru.itis.services.AdminService;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/product")
public class ProductServlet  extends HttpServlet {

    private ProductService productService;
    private CartService cartService;
    private Product product;
    private Long id;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.cartService = (CartService) context.getAttribute("cartService");
        this.productService = (ProductService) context.getAttribute("productService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        id = Long.parseLong(req.getParameter("id"));
        product = productService.getProductById(id);
        List<String> sizes = cartService.getSizes(product);
        req.setAttribute("product", product);
        req.setAttribute("sizes", sizes);
        req.getRequestDispatcher
                ("/WEB-INF/jsp/product.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reqpar = req.getParameter("action");
        String size = req.getParameter("size");

        if (reqpar.equals("save")) {
            System.out.println(size);
            cartService.addToCart(req.getSession(), product.getId(), size);
            resp.sendRedirect("/product?id=" + id);
        }
    }
}
