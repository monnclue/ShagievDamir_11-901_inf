package ru.itis.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.itis.dto.AddressForm;
import ru.itis.models.Address;
import ru.itis.models.OrderForCart;
import ru.itis.models.ProductForCart;
import ru.itis.models.Promo;
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
    private OrderForCart orderForCart;
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
        this.orderForCart = cartService.generateOrderForCart(products);
        req.setAttribute("addresses", addresses);
        req.getRequestDispatcher("/WEB-INF/jsp/cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String reqParam = req.getParameter("action");
        String addressAsJSON;

        if (reqParam.startsWith("address")) {
            AddressForm addressForm = objectMapper.readValue(req.getReader(), AddressForm.class);
            addressService.addAddress(req.getSession(), addressForm);
            addressAsJSON = objectMapper
                    .writeValueAsString(addressService.getAddresses(req.getSession()));
            resp.setContentType("application/json");
            resp.getWriter().println(addressAsJSON);
        }
        if (reqParam.startsWith("ship")) {
            OrderForCart orderForCartFromAjax = objectMapper.readValue(req.getReader(), OrderForCart.class);
            cartService.editPriceShipMethod(this.orderForCart, orderForCartFromAjax.getShippingMethod());
        }
        if (reqParam.startsWith("order")) {
            AddressForm addressForm = objectMapper.readValue(req.getReader(), AddressForm.class);
            if (addressForm.getCity().length() == 0 || addressForm.getCountry().length() == 0 ||
            addressForm.getFirstName().length() == 0 || addressForm.getLastName().length() == 0 ||
            addressForm.getPostcode().length() == 0 || addressForm.getPhone().length() == 0) {
                resp.getWriter().print("emptyField");
            } else {
                Address address = addressService.getAddressOrSaveIfDoesntExist(req.getSession(), addressForm);
                cartService.generateOrder(req.getSession(), address, this.orderForCart);
                resp.sendRedirect("/order");
            }
        }
        if (reqParam.startsWith("promo")) {
            Promo promo = objectMapper.readValue(req.getReader(), Promo.class);
            boolean exist = cartService.getPromoPrice(promo.getCode()) != -1;
            if (exist) {
                resp.getWriter().print("exist " +
                        cartService.getPromoPrice(promo.getCode()));
                this.orderForCart.setPromoPrice(cartService.getPromoPrice(promo.getCode()));
            }
            else resp.getWriter().print("notfound");


        }
        if (reqParam.startsWith("delete")) {
            String id = req.getParameter("prod");

            resp.sendRedirect("/cart");
        }

    }
}
