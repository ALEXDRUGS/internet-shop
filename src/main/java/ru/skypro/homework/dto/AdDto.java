package ru.skypro.homework.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class AdDto {
    Integer author;
    String image;
    @Id
    Integer pk;
    Integer price;
    String title;
}