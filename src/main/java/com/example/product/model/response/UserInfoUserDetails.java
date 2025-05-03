package com.example.product.model.response;

import com.example.product.model.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Set;
import java.util.UUID;


@Getter
@NoArgsConstructor
public class UserInfoUserDetails implements UserDetails {

    private UUID id;
    private String email;
    private String password;
    private Set<GrantedAuthority> authorities;

    /**
     * Constructs a new UserInfoUserDetails instance based on the provided User object.
     *
     * @param user The User object containing the user information.
     */
    public UserInfoUserDetails(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
