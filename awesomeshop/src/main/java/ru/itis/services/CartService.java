package ru.itis.services;

import ru.itis.models.Address;
import ru.itis.models.OrderForCart;
import ru.itis.models.Product;
import ru.itis.models.ProductForCart;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface CartService {
    void addToCart(HttpSession session, Long productId, String size);
    List<ProductForCart> getChosen(HttpSession session);
    List<String> getSizes(Product product);
    OrderForCart generateOrderForCart(List<ProductForCart> products);
    void editPriceShipMethod(OrderForCart orderForCart, String ship);
    int getPromoPrice(String code);
    void generateOrder(HttpSession session, Address address, OrderForCart orderForCart);
}
