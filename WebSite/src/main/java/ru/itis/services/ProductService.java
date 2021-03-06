package ru.itis.services;

import ru.itis.dto.ProductForm;
import ru.itis.models.Product;

import java.util.List;

public interface ProductService {
    void createProduct(ProductForm productForm);
    void deleteProductByID(Long id);
    Product getProductById(Long id);
    List<Product> getAllProducts();
    List<Product> getAllByName(String name);
    List<Product> getAllByType(String type);
}

