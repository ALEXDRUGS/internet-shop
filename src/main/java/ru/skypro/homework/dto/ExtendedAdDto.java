package ru.skypro.homework.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class ExtendedAdDto {
    @Id
    Integer pk;
    String authorFirstName;
    String authorLastName;
    String description;
    String email;
    String image;
    String phone;
    Integer price;
    String title;
}