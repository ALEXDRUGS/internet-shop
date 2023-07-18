package ru.skypro.homework.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.repositories.ImageRepository;

import java.io.IOException;

@Service
public class ImageService {
    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public void createImage(MultipartFile image) {
        Image i = new Image();
        try {
            i.setImage(image.getBytes());
            i.setUsername(AuthServiceImpl.getAuthUser().getUsername());
            imageRepository.saveAndFlush(i);
            AuthServiceImpl.getAuthUser().setAvatar(i);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Image findByUsername(String username) {
        return imageRepository.findByUsername(username);
    }
}
