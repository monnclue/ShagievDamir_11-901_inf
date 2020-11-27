package ru.itis.services;

import ru.itis.models.Order;

import javax.servlet.http.HttpSession;

public interface OrderService {
    Order getOrderForAccount(HttpSession session);
    void deleteNotPayed(HttpSession session);
}
