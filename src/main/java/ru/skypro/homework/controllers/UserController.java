package ru.skypro.homework.controllers;

import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.dto.UserUpdateDto;
import ru.skypro.homework.services.impl.UserService;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Обновление пароля
     *
     * @param currentPassword String
     * @param newPassword String
     */
    @PostMapping("/set_password")
    public void setPassword(@RequestParam("currentPassword") String currentPassword,
                            @RequestParam("newPassword") String newPassword) {
        userService.updatePassword(currentPassword, newPassword);
    }

    /**
     * Получение представления пользователя
     *
     * @return UserDto instance
     */
    @GetMapping("/me")
    public UserDto getUser() {
        return userService.getUserDto();
    }

    /**
     * Обновление пользователя
     *
     * @param updateDto UserUpdateDto
     * @return UserUpdateDto instance
     */
    @PatchMapping("/me")
    public UserUpdateDto updateUser(@RequestBody UserUpdateDto updateDto) {
        return userService.updateUser(updateDto);
    }

    /**
     * Обновление аватара пользователя
     *
     * @param avatar MultipartFile
     */
    @SneakyThrows
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.IMAGE_JPEG_VALUE)
    public void updateAvatar(@RequestParam("image") MultipartFile avatar) {
        userService.updateAvatar(avatar);
    }
}