package ru.skypro.homework.utils;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;
import ru.skypro.homework.services.impl.AuthServiceImpl;

@Service
public class MappingUtils {
    public UserDto mapToUserDto(@NotNull User entity) {
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setPhone(entity.getPhone());
        dto.setImage(entity.getAvatarReference());
        return dto;
    }

    public UserUpdateDto mapToUserUpdateDto(@NotNull User entity) {
        UserUpdateDto dto = new UserUpdateDto();
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setPhone(entity.getPhone());
        return dto;
    }

    public User mapFromUpdateDto(@NotNull UserUpdateDto dto) {
        User entity = AuthServiceImpl.getAuthUser();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPhone(dto.getPhone());
        return entity;
    }

    public AdDto mapToAdDto(@NotNull Ad entity) {
        AdDto dto = new AdDto();
        dto.setPk(entity.getAdId());
        dto.setAuthor(AuthServiceImpl.getAuthUser().getId());
        dto.setImage(entity.getAvatarReference());
        dto.setTitle(entity.getTitle());
        dto.setPrice(entity.getPrice());
        return dto;
    }

    public ExtendedAdDto mapToExtendedAdDto(@NotNull Ad entity) {
        User user = AuthServiceImpl.getAuthUser();
        ExtendedAdDto dto = new ExtendedAdDto();
        dto.setPk(entity.getAdId());
        dto.setAuthorFirstName(dto.getAuthorFirstName());
        dto.setAuthorLastName(dto.getAuthorLastName());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setTitle(entity.getTitle());
        dto.setPrice(entity.getPrice());
        dto.setDescription(entity.getDescription());
        dto.setImage(entity.getImageReference());
        return dto;
    }

    public Ad mapToAd(@NotNull CreateOrUpdateAdDto dto, @NotNull String imageReference) {
        User user = AuthServiceImpl.getAuthUser();
        Ad ad = new Ad();
        ad.setUserId(user);
        ad.setAvatarReference(user.getAvatarReference());
        ad.setPrice(dto.getPrice());
        ad.setTitle(dto.getTitle());
        ad.setDescription(dto.getDescription());
        ad.setImageReference(imageReference);
        return ad;
    }

    public CommentDto mapToCommentDto(@NotNull Comment entity) {
        CommentDto dto = new CommentDto();
        dto.setAuthor(AuthServiceImpl.getAuthUser().getId());
        dto.setAuthorFirstName(entity.getFirstName());
        dto.setAuthorImage(entity.getUserAvatarReference());
        dto.setPk(entity.getCommentId());
        dto.setCreatedAt(entity.getDateOfCreation());
        dto.setText(entity.getText());
        return dto;
    }
}