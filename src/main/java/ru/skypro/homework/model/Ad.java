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
    @ManyToOne
    private Image avatar;
    private Integer price;
    private String title;
    private String description;
    @OneToOne
    private Image image;
}