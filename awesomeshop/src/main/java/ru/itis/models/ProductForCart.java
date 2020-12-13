package ru.itis.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class ProductForCart {
    private Long rel_id;
    private Long account_id;
    private Long productSize_id;
    private String size;
    private String name;
    private String imageURL;
    private Integer price;
}
