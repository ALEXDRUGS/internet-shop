package ru.skypro.homework.controllers;

import lombok.RequiredArgsConstructor;
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
     * @return String reference
     */
    @GetMapping("/image/{id}")
    public String getImage(@PathVariable Integer id) {
        return imageService.getImageReference(id);
    }
}