package ru.itis.repositories;

import ru.itis.dto.ProductForCartForm;
import ru.itis.models.Product;
import ru.itis.models.ProductForCart;
import ru.itis.models.ProductSize;

import java.util.List;

public interface SizeRepository extends CrudRepository<ProductSize>{
    List<ProductForCart> findByAccountId(Long id);
    void pickProduct(ProductForCartForm productForCartForm);
    List<ProductSize> getProductsSizes(Long id);
}
