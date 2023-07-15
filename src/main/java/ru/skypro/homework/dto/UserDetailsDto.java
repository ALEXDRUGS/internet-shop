package ru.skypro.homework.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@RequiredArgsConstructor
@Component
public class UserDetailsDto {
    private final String username;
    private final String password;
    private final Integer userId;
    private final Role role;
}