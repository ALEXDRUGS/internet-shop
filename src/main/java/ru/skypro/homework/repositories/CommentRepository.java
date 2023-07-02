package ru.skypro.homework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findAllByAdId(Integer id);

    Comment getByAdIdAndCommentId(Integer adId, Integer commId);

    void deleteByAdIdAndCommentId(Integer adId, Integer commId);
}