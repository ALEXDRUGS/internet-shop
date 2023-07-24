package ru.skypro.homework.services.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.RegisterReq;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.model.User;
import ru.skypro.homework.services.AuthService;

import javax.persistence.Lob;

@Service
public class AuthServiceImpl implements AuthService {
  private static UserService userService;
  private final UserDetailsServiceImpl userDetailsService;
  private final PasswordEncoder encoder;

  public AuthServiceImpl(UserDetailsServiceImpl userDetailsService, UserService userService, PasswordEncoder passwordEncoder) {
    AuthServiceImpl.userService = userService;
    this.userDetailsService = userDetailsService;
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

  @Lob
  public static User getAuthUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return userService.getUserByUsername(authentication.getName());
  }
}