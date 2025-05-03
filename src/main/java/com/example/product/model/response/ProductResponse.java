package com.example.product.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ProductResponse {

    private UUID id;
    private String name;
    private String description;
    private Double price;
    private Long quantity;
}
