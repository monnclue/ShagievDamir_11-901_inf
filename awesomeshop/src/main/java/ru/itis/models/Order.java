package ru.itis.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Order {
    private int price;
    private Long accountId;
    private Long addressId;
    private boolean isOrderShipped;
    private String shipMethod;
}
