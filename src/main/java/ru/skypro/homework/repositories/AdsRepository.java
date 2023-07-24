package ru.skypro.homework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.Ad;

import java.util.List;

@Repository
public interface AdsRepository extends JpaRepository<Ad, Integer> {
    Ad getByUserId(Integer userId);
    Ad getByAdId(Integer adId);
    List<Ad> findAllByUserId(Integer userId);
}