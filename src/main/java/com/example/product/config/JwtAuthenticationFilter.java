package com.example.product.config;

import com.example.product.service.impl.UserDetailServiceImpl;
import com.example.product.utility.JwtHelper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter  {

    private final UserDetailServiceImpl userDetailService;

    @Override
    protected void doFilterInternal(final @NonNull HttpServletRequest request, final @NonNull HttpServletResponse response,
                                    final @NonNull FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwtToken;
        final String userEmail;
        final int jwtTokenSize = 7;

        if (ObjectUtils.isEmpty(authHeader) || !StringUtils.startsWithIgnoreCase(authHeader, "Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        jwtToken = authHeader.substring(jwtTokenSize);
        userEmail = JwtHelper.extractUsername(jwtToken);
        if (!ObjectUtils.isEmpty(userEmail) && SecurityContextHolder.getContext().getAuthentication() == null) {
            final UserDetails userDetails = userDetailService.loadUserByUsername(userEmail);
            if (Boolean.TRUE.equals(JwtHelper.validateToken(jwtToken, userDetails))) {
                final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
