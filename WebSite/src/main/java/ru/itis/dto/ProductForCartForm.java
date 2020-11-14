package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ProductForCartForm {
    private Long accountId;
    private Long productId;
    private String productSize;
}
