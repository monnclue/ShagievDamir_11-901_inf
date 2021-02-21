package ru.itis.springbootdemo.repositories;

import ru.itis.springbootdemo.models.Product;

import java.util.List;

public interface ProductsRepository extends CrudRepository<Product> {
    List<Product> findByType(String type);
    List<Product> findByName(String name);
}
