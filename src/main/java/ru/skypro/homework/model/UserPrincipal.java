package ru.skypro.homework.model;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.UserDetailsDto;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Component
public class UserPrincipal implements UserDetails {
    private final UserDetailsDto userDetailsDto;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + userDetailsDto.getRole().name()));
    }

    @Override
    public String getPassword() {
        return userDetailsDto.getPassword();
    }

    @Override
    public String getUsername() {
        return userDetailsDto.getUsername();
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