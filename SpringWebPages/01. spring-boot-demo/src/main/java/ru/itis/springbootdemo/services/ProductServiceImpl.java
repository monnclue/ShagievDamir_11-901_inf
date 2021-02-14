package ru.itis.springbootdemo.services;

import ru.itis.springbootdemo.dto.ProductForm;
import ru.itis.springbootdemo.repositories.ProductsRepository;
import ru.itis.springbootdemo.models.Product;


import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {

    private ProductsRepository productsRepository;

    public ProductServiceImpl(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public void createProduct(ProductForm productForm) {

        Product product = Product.builder()
                .name(productForm.getName())
                .type(productForm.getType())
                .imageURL(productForm.getImageURL())
                .description(productForm.getDescription())
                .price(productForm.getPrice()).build();
        productsRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productsRepository.findAll();
    }

    @Override
    public List<Product> getAllByName(String name) {
        if (name.equals("") || name.equals(" ")) {
            return null;
        }
        return productsRepository.findByName(name);
    }

    @Override
    public List<Product> getAllByType(String type) {
        return productsRepository.findByType(type);
    }


    public void deleteProductByID(Long id) {
        productsRepository.delete(id);
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> productOptional = productsRepository.findById(id);
        return productOptional.orElse(null);
    }

}
