package ru.skypro.homework.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adId;
    @ManyToOne
    private User user;
    private String avatarReference;
    private Integer price;
    private String title;
    private String description;
    private String imageReference;
}