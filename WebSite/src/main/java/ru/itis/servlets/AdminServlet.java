package ru.itis.servlets;

import ru.itis.dto.ProductForm;
import ru.itis.models.Product;
import ru.itis.services.ProductService;
import ru.itis.services.SignInService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private ProductService productService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.productService = (ProductService) context.getAttribute("productService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = productService.getAllProducts();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setAttribute("products",products);

        req.getRequestDispatcher("/WEB-INF/jsp/admin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String reqParameter = req.getParameter("action");

        if (reqParameter.equals("input")) {
            if(!req.getParameter("price").isEmpty()) {
                ProductForm productForm = ProductForm.builder()
                        .name(req.getParameter("name"))
                        .type(req.getParameter("type"))
                        .imageURL(req.getParameter("imageURL"))
                        .description(req.getParameter("description"))
                        .price(Integer.parseInt(req.getParameter("price"))).build();
                productService.createProduct(productForm);
            }
            resp.sendRedirect("/admin");
        }
        else if (reqParameter.startsWith("delete")) {
            Long id = Long.parseLong(reqParameter.split("=")[1]);
            productService.deleteProductByID(id);
            resp.sendRedirect("/admin");
        }


    }
}
