package ru.skypro.homework.services.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skypro.homework.model.UserPrincipal;
import ru.skypro.homework.utils.MappingUtils;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;
    private final MappingUtils mappingUtils;

    public UserDetailsServiceImpl(UserService userService, MappingUtils mappingUtils) {
        this.userService = userService;
        this.mappingUtils = mappingUtils;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserPrincipal(mappingUtils.mapToUserDetailsDto(userService.getUserByUsername(username)));
    }
}
