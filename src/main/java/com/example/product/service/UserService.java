package com.example.product.service;

import com.example.product.common.EntityResponse;
import com.example.product.model.request.LoginRequest;
import com.example.product.model.response.LoginResponseModel;

public interface UserService {

    EntityResponse<LoginResponseModel, Void> handleLogin(LoginRequest loginRequest);
}
