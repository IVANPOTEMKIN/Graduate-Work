package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.ImageEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Path;

public interface ImageService {

    ImageEntity saveImage(MultipartFile file) throws IOException;

    ImageEntity saveToDB(MultipartFile file,
                         Path path) throws IOException;

    void downloadFromDirectory(HttpServletResponse response,
                               String path) throws IOException;
}