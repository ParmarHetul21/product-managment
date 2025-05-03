package com.example.product.service.impl;

import com.example.product.model.response.UserInfoUserDetails;
import com.example.product.repository.UserRepository;
import com.example.product.utility.Translator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final Translator translator;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmailIgnoreCase(email)
                .map(UserInfoUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(translator.toLocal("user.data.not.found")));
    }
}
