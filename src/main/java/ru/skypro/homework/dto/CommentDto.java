package ru.skypro.homework.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDto {
    Integer author;
    String authorImage;
    String authorFirstName;
    LocalDateTime createdAt;
    Integer pk;
    String text;
}