package ru.itis.services;

import ru.itis.models.Order;
import ru.itis.models.Product;
import ru.itis.models.ProductForCart;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface CartService {
    void addToCart(HttpSession session, Long productId, String size);
    List<ProductForCart> getChosen(HttpSession session);
    List<String> getSizes(Product product);
    Order generateOrder(List<ProductForCart> products);
    void editPriceShipMethod(Order order, String ship);
}
