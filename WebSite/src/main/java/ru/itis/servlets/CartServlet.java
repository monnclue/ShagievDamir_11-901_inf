package ru.itis.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.itis.dto.AddressForm;
import ru.itis.models.Address;
import ru.itis.models.Order;
import ru.itis.models.ProductForCart;
import ru.itis.services.AddressService;
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
    private AddressService addressService;
    private Order order;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.productService = (ProductService) context.getAttribute("productService");
        this.cartService = (CartService) context.getAttribute("cartService");
        this.addressService = (AddressService) context.getAttribute("addressService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProductForCart> products = cartService.getChosen(req.getSession());
        List<Address> addresses = addressService.getAddresses(req.getSession());
        req.setAttribute("products", products);
        this.order = cartService.generateOrder(products);
        req.setAttribute("addresses", addresses);
        req.getRequestDispatcher("/WEB-INF/jsp/cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String reqParam = req.getParameter("action");
        String addressAsJSON = null;

        if (reqParam.startsWith("address")) {
            System.out.println(req.getParameterMap().keySet());
            AddressForm addressForm = objectMapper.readValue(req.getReader(), AddressForm.class);
            addressService.addAddress(req.getSession(), addressForm);
            addressAsJSON = objectMapper
                    .writeValueAsString(addressService.getAddresses(req.getSession()));
        }
        if (reqParam.startsWith("ship")) {
            Order orderFromAjax = objectMapper.readValue(req.getReader(), Order.class);
            cartService.editPriceShipMethod(this.order, orderFromAjax.getShippingNethod());

        }
        resp.setContentType("application/json");
        resp.getWriter().println(addressAsJSON);
    }
}
