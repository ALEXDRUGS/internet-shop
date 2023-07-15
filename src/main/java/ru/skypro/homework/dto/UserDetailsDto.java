package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
public class UserDetailsDto {
    private String username;
    private String password;
    private Integer userId;
    private Role role;

    public UserDetailsDto() {
    }
}