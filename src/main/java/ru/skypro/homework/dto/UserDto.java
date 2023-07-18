package ru.skypro.homework.dto;

import lombok.Data;
import ru.skypro.homework.model.Image;

@Data
public class UserDto {
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private Image image;
}