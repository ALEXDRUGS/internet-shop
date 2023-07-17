package ru.skypro.homework.services.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.RegisterReq;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.services.AuthService;


@Service
public class AuthServiceImpl implements AuthService {
  private final UserDetailsServiceImpl userDetailsService;
  private final UserService userService;
  private final PasswordEncoder encoder;

  public AuthServiceImpl(UserDetailsServiceImpl userDetailsService, UserService userService, PasswordEncoder passwordEncoder) {
    this.userDetailsService = userDetailsService;
    this.userService = userService;
    this.encoder = passwordEncoder;
  }

  @Override
  public boolean login(String userName, String password) {
    if (userService.getUserByUsername(userName) == null) {
      return false;
    }
    UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
    return encoder.matches(password, userDetails.getPassword());
  }

  @Override
  public boolean register(RegisterReq registerReq, Role role) {
    if (userService.getUserByUsername(registerReq.getUsername()) != null) {
      return false;
    }
    userService.createUser(registerReq, role);
    return true;
  }

  public static ru.skypro.homework.model.User getAuthUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return (ru.skypro.homework.model.User) authentication.getPrincipal();
  }
}