package ru.itis.services;

import ru.itis.models.Product;
import ru.itis.models.ProductSize;
import ru.itis.repositories.SizeRepository;
import ru.itis.repositories.UsersRepository;

import java.util.ArrayList;
import java.util.List;

public class AdminServiceImpl implements AdminService{

    private SizeRepository sizeRepository;

    public AdminServiceImpl(SizeRepository sizeRepository) {
        this.sizeRepository = sizeRepository;
    }

    @Override
    public void setCount(Long prodId, int count, String size) {
        ProductSize productSize = ProductSize.builder()
                .productId(prodId)
                .count(count)
                .size(size)
                .build();
        List<ProductSize> productSizes = sizeRepository.getProductsSizes(prodId);
        List<String> sizes = new ArrayList<>();
        for (ProductSize product: productSizes) {
            sizes.add(product.getSize());
        }
        if (sizes.contains(size)) {
            sizeRepository.update(productSize);
        } else {
            sizeRepository.save(productSize);
        }
    }

    @Override
    public List<ProductSize> getProductsSizes(Product product) {
        return sizeRepository.getProductsSizes(product.getId());
    }
}
