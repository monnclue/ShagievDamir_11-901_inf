package ru.itis.services;

import ru.itis.models.Order;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface ProfileService {
    List<Order> getOrders(HttpSession session);
}
