package ru.skypro.homework.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserUpdateDto {
    private String firstName;
    private String lastName;
    private String phone;
}