package ru.itis.services;

import ru.itis.dto.ProductForCartForm;
import ru.itis.dto.UserDto;
import ru.itis.models.Product;
import ru.itis.models.ProductForCart;
import ru.itis.repositories.CartRepository;
import ru.itis.repositories.UsersRepository;

import javax.servlet.http.HttpSession;
import java.util.List;

public class CartServiceImpl implements CartService {

    private CartRepository cartRepository;
    private UsersRepository usersRepository;

    public CartServiceImpl(CartRepository cartRepository, UsersRepository usersRepository) {
        this.cartRepository = cartRepository;
        this.usersRepository = usersRepository;
    }


    @Override
    public void addToCart(HttpSession session, Long productId, String size) {
        UserDto userDto = (UserDto) session.getAttribute("user");
        Long id = userDto.getId();
        ProductForCartForm productForCartForm = ProductForCartForm.builder()
                .accountId(id)
                .productSize(size)
                .productId(productId)
                .build();
        cartRepository.pickProduct(productForCartForm);
    }

    @Override
    public List<ProductForCart> getChosen(HttpSession session) {
        UserDto userDto = (UserDto) session.getAttribute("user");
        List<ProductForCart> products =
                cartRepository.findByAccountId(userDto.getId());
        return products;
    }

    @Override
    public List<String> getSizes(Product product) {
        return cartRepository.getSizes(product.getId());
    }
}
