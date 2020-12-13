package ru.itis.services;

import ru.itis.dto.UserDto;
import ru.itis.models.Order;
import ru.itis.repositories.OrderRepository;

import javax.servlet.http.HttpSession;

public class OrderServiceImpl implements OrderService{

    private OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order getOrderForAccount(HttpSession session) {
        UserDto userDto = (UserDto) session.getAttribute("user");
        if (orderRepository.findByAccountIdNotPayed(userDto.getId()).isPresent()) {
            return orderRepository.findByAccountIdNotPayed(userDto.getId()).get();
        } else return null;
    }

    @Override
    public void deleteNotPayed(HttpSession session) {
        UserDto userDto = (UserDto) session.getAttribute("user");
        orderRepository.delete(userDto.getId());
    }
}
