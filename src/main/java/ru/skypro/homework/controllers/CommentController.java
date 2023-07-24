package ru.skypro.homework.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.services.impl.AuthServiceImpl;
import ru.skypro.homework.services.impl.CommentService;

import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/ads")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{id}/comments")
    public List<CommentDto> getAdComments(@PathVariable Integer id) {
        return commentService.getAdComments(id);
    }

    @PostMapping("/{id}/comments")
    public CommentDto createComment(@RequestBody String text, @PathVariable Integer id) {
        return commentService.createComment(text, id);
    }

    @DeleteMapping("/{adId}/comments/{commentId}")
    public void deleteComment(@PathVariable Integer adId, @PathVariable Integer commentId) {
        if (isAuthorities(commentId)) {
            commentService.deleteComment(adId, commentId);
        }
    }

    @PatchMapping("/{adId}/comments/{commentId}")
    public CommentDto updateComment(@RequestBody String text, @PathVariable Integer adId, @PathVariable Integer commentId) {
        if (isAuthorities(commentId)) {
            return commentService.updateComment(text, adId, commentId);
        }
        throw new HttpClientErrorException(HttpStatus.FORBIDDEN);
    }

    private boolean isAuthorities(Integer id) {
        return commentService.getByCommentId(id).getUser().equals(AuthServiceImpl.getAuthUser())
                || commentService.getByCommentId(id).getUser().getRole().name().equals("ADMIN");
    }
}