package ru.itis.repositories;

import ru.itis.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductsRepository extends CrudRepository<Product> {
    List<Product> findByType(String type);
    List<Product> findByName(String name);
    List<Product> selectByTopOrders(int topCount);
}
