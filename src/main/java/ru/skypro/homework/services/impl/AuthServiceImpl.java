package ru.skypro.homework.services.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.RegisterReq;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.services.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
  private final UserDetailsServiceImpl userDetailsService;
  private final UserDetailsManager manager;
  private final PasswordEncoder encoder;

  public AuthServiceImpl(UserDetailsServiceImpl userDetailsService, UserDetailsManager manager, PasswordEncoder passwordEncoder) {
    this.userDetailsService = userDetailsService;
    this.manager = manager;
    this.encoder = passwordEncoder;
  }

  @Override
  public boolean login(String userName, String password) {
    if (!manager.userExists(userName)) {
      return false;
    }
    UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
    return encoder.matches(password, userDetails.getPassword());
  }

  @Override
  public boolean register(RegisterReq registerReq, Role role) {
    if (!manager.userExists(registerReq.getUsername())) {
      return false;
    }
    manager.createUser(
        User.builder()
            .passwordEncoder(this.encoder::encode)
            .password(registerReq.getPassword())
            .username(registerReq.getUsername())
            .roles(role.name())
            .build());
    return true;
  }

  public static ru.skypro.homework.model.User getAuthUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return (ru.skypro.homework.model.User) authentication.getPrincipal();
  }
}