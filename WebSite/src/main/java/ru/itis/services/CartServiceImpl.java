package ru.itis.services;

import ru.itis.dto.ProductForCartForm;
import ru.itis.dto.UserDto;
import ru.itis.models.Product;
import ru.itis.models.ProductForCart;
import ru.itis.models.ProductSize;
import ru.itis.repositories.SizeRepository;
import ru.itis.repositories.UsersRepository;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class CartServiceImpl implements CartService {

    private SizeRepository sizeRepository;
    private UsersRepository usersRepository;

    public CartServiceImpl(SizeRepository sizeRepository, UsersRepository usersRepository) {
        this.sizeRepository = sizeRepository;
        this.usersRepository = usersRepository;
    }


    @Override
    public void addToCart(HttpSession session, Long productId, String size) {
        UserDto userDto = (UserDto) session.getAttribute("user");
        Long id = userDto.getId();
        System.out.println(id + " " + size + " " + productId);
        ProductForCartForm productForCartForm = ProductForCartForm.builder()
                .accountId(id)
                .productSize(size)
                .productId(productId)
                .build();
        sizeRepository.pickProduct(productForCartForm);
    }

    @Override
    public List<ProductForCart> getChosen(HttpSession session) {
        UserDto userDto = (UserDto) session.getAttribute("user");
        List<ProductForCart> products =
                sizeRepository.findByAccountId(userDto.getId());
        return products;
    }

    @Override
    public List<String> getSizes(Product product) {
        List<String> sizes = new ArrayList<>();
        List<ProductSize> productSizes = sizeRepository.getProductsSizes(product.getId());
        for (ProductSize productSize : productSizes) {
            sizes.add(productSize.getSize() + "");
        }
        return sizes;
    }
}
