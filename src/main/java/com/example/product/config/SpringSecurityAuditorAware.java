package com.example.product.config;

import com.example.product.model.entity.User;
import com.example.product.model.response.UserInfoUserDetails;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SpringSecurityAuditorAware implements AuditorAware<User> {

    @Override
    public Optional<User> getCurrentAuditor() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .filter(authentication -> !authentication.getPrincipal().toString().equals("anonymousUser"))
                .map(Authentication::getPrincipal)
                .map(UserInfoUserDetails.class::cast)
                .map(userInfoUserDetails -> User.builder().id(userInfoUserDetails.getId()).build());
    }
}
