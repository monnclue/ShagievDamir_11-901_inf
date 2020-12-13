package ru.itis.services;

import ru.itis.models.Product;
import ru.itis.models.ProductSize;

import java.util.List;

public interface AdminService {
    void setCount(Long id, int count, String size);
    List<ProductSize> getProductsSizes(Product product);
}
