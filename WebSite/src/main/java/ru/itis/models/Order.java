package ru.itis.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Order {
    private int totalPrice;
    private String shippingNethod;

    public void increasePrice(int price) {
        totalPrice += price;
    }
    public void decreasePrice(int price) {
        totalPrice -= price;
    }

}

