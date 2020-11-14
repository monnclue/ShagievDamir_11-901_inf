package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ProductForm {
    private String name;
    private String type;
    private String imageURL;
    private String description;
    private Integer price;
}
