package ru.itis.services;

import ru.itis.dto.UserDto;
import ru.itis.models.Order;
import ru.itis.repositories.OrderRepository;

import javax.servlet.http.HttpSession;
import java.util.List;

public class ProfileServiceImpl implements ProfileService{

    private OrderRepository orderRepository;

    public ProfileServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getOrders(HttpSession session) {
        UserDto userDto = (UserDto) session.getAttribute("user");
        if (orderRepository.findByAccountIdPayed(userDto.getId()).isEmpty()) {
            return null;
        }
        return orderRepository.findByAccountIdPayed(userDto.getId());
    }
}
