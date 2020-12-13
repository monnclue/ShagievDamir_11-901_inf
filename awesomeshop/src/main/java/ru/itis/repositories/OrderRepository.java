package ru.itis.repositories;

import ru.itis.models.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends CrudRepository<Order>{
    Optional<Order> findByAccountIdNotPayed(Long id);
    List<Order> findByAccountIdPayed(Long id);

}
