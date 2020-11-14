package ru.itis.repositories;

import ru.itis.dto.ProductForCartForm;
import ru.itis.models.Product;
import ru.itis.models.ProductForCart;

import java.util.List;

public interface CartRepository extends CrudRepository{
    List<ProductForCart> findByAccountId(Long id);
    void pickProduct(ProductForCartForm productForCartForm);
    List<String> getSizes(Long id);
}
