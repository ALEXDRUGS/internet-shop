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
    @ManyToOne(cascade = CascadeType.REMOVE)
    User user;
    @ManyToOne(cascade = CascadeType.REMOVE)
    Ad adId;
    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    Image avatar;
    String firstName;
    LocalDateTime dateOfCreation;
    String text;
}