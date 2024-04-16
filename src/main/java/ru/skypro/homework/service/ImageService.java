package ru.skypro.homework.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.ImageEntity;

public interface ImageService {

    ImageEntity saveImage(MultipartFile file);

    ResponseEntity<byte[]> downloadImage(int id);

    void deleteImage(int id);
}