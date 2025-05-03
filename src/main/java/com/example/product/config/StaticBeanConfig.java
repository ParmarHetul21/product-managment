package com.example.product.config;

import com.example.product.utility.JwtHelper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class StaticBeanConfig {

    @Value("${jwt.secret.key}")
    String jwtSecretKey;

    @Value("${jwt.expiry.time}")
    Long jwtTokenExpiryTimeInMillis;

    @PostConstruct
    public void init() {
        JwtHelper.setJwtSecretKey(jwtSecretKey);
        JwtHelper.setJwtTokenExpiryTimeInMillis(jwtTokenExpiryTimeInMillis);
    }
}
