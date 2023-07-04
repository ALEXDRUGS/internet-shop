package ru.skypro.homework.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class CommentDto {
    Integer author;
    String authorImage;
    String authorFirstName;
    LocalDateTime createdAt;
    @Id
    Integer pk;
    String text;
}