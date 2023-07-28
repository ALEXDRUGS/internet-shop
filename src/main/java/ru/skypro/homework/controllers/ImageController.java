package ru.skypro.homework.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.services.impl.ImageService;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    /**
     * Получение картинки
     *
     * @param id Integer
     * @return FileSystemResource reference
     */
    @GetMapping("/image/{id}")
    public FileSystemResource getImage(@PathVariable Integer id) {
        return imageService.getImage(id);
    }
}