package com.example.product.service.impl;

import com.example.product.common.CommonConstant;
import com.example.product.common.CommonErrorConstant;
import com.example.product.common.EntityResponse;
import com.example.product.exception.ProductServiceException;
import com.example.product.model.entity.User;
import com.example.product.model.request.LoginRequest;
import com.example.product.model.response.LoginResponseModel;
import com.example.product.model.response.UserInfoUserDetails;
import com.example.product.repository.UserRepository;
import com.example.product.service.UserService;
import com.example.product.utility.JwtHelper;
import com.example.product.utility.Translator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.example.product.common.CommonErrorConstant.INVALID_CREDENTIALS;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final UserDetailServiceImpl userDetailService;
    private final Translator translator;

    @Override
    public EntityResponse<LoginResponseModel, Void> handleLogin(LoginRequest loginRequest) {
        try {
            Authentication authentication = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
            this.authenticationManager.authenticate(authentication);
            User user = findUserByEmail(loginRequest.getEmail());
            UserInfoUserDetails userInfoUserDetails = new UserInfoUserDetails(user);
            String token = generateToken(loginRequest, userInfoUserDetails.getId());
            LoginResponseModel loginResponseModel =
                    LoginResponseModel.builder().token(token).username(user.getUsername()).build();
            return EntityResponse.setEntityResponse(HttpStatus.OK.value(),
                    translator.toLocal("login.successful"), loginResponseModel);
        } catch (AuthenticationException exception) {
            throw new ProductServiceException(INVALID_CREDENTIALS.getErrorCode(),
                    translator.toLocal(INVALID_CREDENTIALS.getErrorMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    public String generateToken(LoginRequest loginRequest, UUID userId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CommonConstant.USER_ID, userId);
        return JwtHelper.createToken(claims, loginRequest);
    }

    private User findUserByEmail(String email) {
        return userRepository
                .findByEmailIgnoreCase(email)
                .orElseThrow(() -> new ProductServiceException(CommonErrorConstant.USER_NOT_FOUND.getErrorCode(),
                        translator.toLocal(CommonErrorConstant.USER_NOT_FOUND.getErrorMessage()), HttpStatus.NOT_FOUND));
    }
}
