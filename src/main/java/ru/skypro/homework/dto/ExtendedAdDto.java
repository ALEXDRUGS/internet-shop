package ru.skypro.homework.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import ru.skypro.homework.model.Image;

@Data
public class ExtendedAdDto {
    @Id
    Integer pk;
    String authorFirstName;
    String authorLastName;
    String description;
    String email;
    Image image;
    String phone;
    Integer price;
    String title;
}