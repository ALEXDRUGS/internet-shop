package ru.skypro.homework.model;

import lombok.Data;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String avatarReference;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    public String getRole() {
        return this.role.name();
    }

    public void setRole(String role) {
        this.role = Role.valueOf(role);
    }
}