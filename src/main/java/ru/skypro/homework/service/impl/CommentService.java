package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repositories.CommentRepository;
import ru.skypro.homework.utils.MappingUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final MappingUtils mappingUtils;

    public CommentService(CommentRepository commentRepository, MappingUtils mappingUtils) {
        this.commentRepository = commentRepository;
        this.mappingUtils = mappingUtils;
    }

    public List<CommentDto> getAdComments(Integer id) {
        return commentRepository.findAllByAdId(id).stream().map(mappingUtils::mapToCommentDto).collect(Collectors.toList());
    }

    public CommentDto createComment(String text, Integer adId) {
        User user = AuthServiceImpl.getAuthUser();
        Comment comment = new Comment();
        comment.setUserId(user.getId());
        comment.setFirstName(user.getFirstName());
        comment.setUserAvatarReference(user.getAvatarReference());
        comment.setDateOfCreation(LocalDateTime.now());
        comment.setText(text);
        comment.setAdId(adId);
        commentRepository.saveAndFlush(comment);
        return mappingUtils.mapToCommentDto(comment);
    }

    public void deleteComment(Integer adId, Integer commentId) {
        commentRepository.deleteByAdIdAndCommentId(adId, commentId);
    }

    public CommentDto updateComment(String text, Integer adId, Integer commentId) {
        commentRepository.getByAdIdAndCommentId(adId, commentId).setText(text);
        return mappingUtils.mapToCommentDto(commentRepository.getByAdIdAndCommentId(adId, commentId));
    }
}