package ru.itis.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.itis.models.Product;
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

@WebServlet ("/shop")
public class ShopPageServlet extends HttpServlet {
    private ProductService productService;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.productService = (ProductService) context.getAttribute("productService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        req.getRequestDispatcher("/WEB-INF/jsp/shop.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String reqParam = req.getParameter("action");
        Product product = objectMapper.readValue(req.getReader(), Product.class);
        String productsAsJson = null;
        if (reqParam.startsWith("name")) {
            productsAsJson = objectMapper
                    .writeValueAsString(productService.getAllByName(product.getName()));
        } else if (reqParam.startsWith("cat")) {
            productsAsJson= objectMapper
                    .writeValueAsString(productService.getAllByType(product.getType()));
        }
        resp.setContentType("application/json");
        resp.getWriter().println(productsAsJson);
    }
}
