package ru.skypro.homework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User getByPassword(String pass);

    User findByUsername(String username);
}