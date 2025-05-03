package com.example.product.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AddOrEditProductRequest {

    @NotEmpty(message = "{name.mandatory}")
    private String name;
    @Length(max = 500, message = "{description.max.size}")
    private String description;
    @NotNull(message = "{price.mandatory}")
    private Double price;
    @NotNull(message = "{quantity.mandatory}")
    private Long quantity;
}
