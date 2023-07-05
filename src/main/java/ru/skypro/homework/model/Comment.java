package ru.skypro.homework.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer commentId;
    @ManyToOne
    User user;
    @ManyToOne
    Ad adId;
    String userAvatarReference;
    String firstName;
    LocalDateTime dateOfCreation;
    String text;
}