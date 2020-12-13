package ru.itis.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class OrderForCart {
    private int price;
    private String shippingMethod;
    private int promoPrice;


    public int getTotalPrice() {
        int shipPrice = shippingMethod.equals("pochta") ? 300 : 0;
        return price + shipPrice - promoPrice;
    }
}

