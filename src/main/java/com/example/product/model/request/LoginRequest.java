package com.example.product.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoginRequest {

    @NotBlank
    @Size(max = 320)
    private String email;

    @NotBlank
    private String password;
}
