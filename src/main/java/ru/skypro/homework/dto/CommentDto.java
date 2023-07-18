package ru.skypro.homework.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import ru.skypro.homework.model.Image;

import java.time.LocalDateTime;

@Data
public class CommentDto {
    Integer author;
    Image authorImage;
    String authorFirstName;
    LocalDateTime createdAt;
    @Id
    Integer pk;
    String text;
}