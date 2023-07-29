package ru.skypro.homework.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adId;
    @ManyToOne(cascade = CascadeType.REMOVE)
    private User user;
    @ManyToOne(cascade = CascadeType.REMOVE)
    private Image avatar;
    private Integer price;
    private String title;
    private String description;
    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Image image;
}