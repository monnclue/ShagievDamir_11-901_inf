package ru.itis.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class ProductSize {
    private Long id;
    private Long productId;
    private Integer count;
    private String size;
}
