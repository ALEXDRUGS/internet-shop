package ru.skypro.homework.repositories;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@Repository
public class FileSystemRepository {
    @Value("${upload.path}")
    private String UPLOAD_PATH;

    public String save(byte[] content, String imageName) throws Exception {
        Path newFile = Paths.get(UPLOAD_PATH + new Date().getTime() + "-" + imageName + ".jpg");
        Files.createDirectories(newFile.getParent());
        Files.write(newFile, content);
        return newFile.toAbsolutePath().toString();
    }
}