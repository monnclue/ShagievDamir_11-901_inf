package ru.itis.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Product {
    private Long id;
    private String name;
    private String type;
    private String imageURL;
    private String description;
    private Integer price;
}
