package com.example.product.controller;


import com.example.product.common.EntityResponse;
import com.example.product.model.request.LoginRequest;
import com.example.product.model.response.LoginResponseModel;
import com.example.product.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "User")
@RequestMapping("/api/V1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    @Operation(summary = "User Login")
    public EntityResponse<LoginResponseModel, Void> handleLogin(@RequestBody LoginRequest loginRequest) {
        return userService.handleLogin(loginRequest);
    }
}
