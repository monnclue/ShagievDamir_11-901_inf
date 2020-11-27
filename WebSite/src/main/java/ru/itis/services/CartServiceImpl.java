package ru.itis.services;

import ru.itis.dto.ProductForCartForm;
import ru.itis.dto.UserDto;
import ru.itis.models.*;
import ru.itis.repositories.OrderRepository;
import ru.itis.repositories.PromocodeRepository;
import ru.itis.repositories.SizeRepository;
import ru.itis.repositories.UsersRepository;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class CartServiceImpl implements CartService {

    private SizeRepository sizeRepository;
    private UsersRepository usersRepository;
    private PromocodeRepository promocodeRepository;
    private OrderRepository orderRepository;

    public CartServiceImpl(SizeRepository sizeRepository,
                           UsersRepository usersRepository,
                           PromocodeRepository promocodeRepository,
                           OrderRepository orderRepository) {
        this.sizeRepository = sizeRepository;
        this.usersRepository = usersRepository;
        this.promocodeRepository = promocodeRepository;
        this.orderRepository = orderRepository;
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

    @Override
    public OrderForCart generateOrderForCart(List<ProductForCart> products) {
        int totalPrice = 0;
        for (ProductForCart product:products) {
            totalPrice += product.getPrice();
        }
        return OrderForCart.builder().price(totalPrice).build();
    }

    @Override
    public void editPriceShipMethod(OrderForCart orderForCart, String ship) {
        orderForCart.setShippingMethod(ship);
    }

    @Override
    public int getPromoPrice(String code) {
        if (promocodeRepository.getPromoByCode(code).isPresent()) {
            return promocodeRepository.getPromoByCode(code).get().getPrice();
        } else return -1;
    }

    @Override
    public void generateOrder(HttpSession session, Address address, OrderForCart orderForCart) {
        UserDto userDto = (UserDto) session.getAttribute("user");
        Order order = Order.builder().accountId(userDto.getId())
                .isOrderShipped(false)
                .addressId(address.getId())
                .price(orderForCart.getTotalPrice())
                .shipMethod(orderForCart.getShippingMethod())
                .build();
        orderRepository.save(order);
    }


}
