package ru.skypro.homework.services.impl;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.repositories.FileSystemRepository;
import ru.skypro.homework.repositories.ImageRepository;

@Service
public class ImageService {
    private final ImageRepository imageRepository;
    private final FileSystemRepository fileSystemRepository;
    public ImageService(ImageRepository imageRepository, FileSystemRepository fileSystemRepository) {
        this.imageRepository = imageRepository;
        this.fileSystemRepository = fileSystemRepository;
    }

    public Image createImage(MultipartFile image) throws Exception {
        Image i = new Image();
        i.setName(image.getName());
        i.setImage(image.getBytes());
        i.setImageReference(fileSystemRepository.save(image.getBytes(), image.getName()));
        return imageRepository.saveAndFlush(i);
    }

    public FileSystemResource getImage(Integer id) {
        return new FileSystemResource(imageRepository.getReferenceById(id).getImageReference());
    }
}