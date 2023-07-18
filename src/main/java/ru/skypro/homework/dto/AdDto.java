package ru.skypro.homework.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import ru.skypro.homework.model.Image;

@Data
public class AdDto {
    Integer author;
    Image image;
    @Id
    Integer pk;
    Integer price;
    String title;
}