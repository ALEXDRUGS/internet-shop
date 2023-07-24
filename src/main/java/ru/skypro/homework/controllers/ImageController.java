package ru.skypro.homework.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.services.impl.ImageService;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;
    @GetMapping("/image/{id}")
    public String getImage(@PathVariable Integer id) {
        return imageService.getImageReference(id);
    }
}