package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.ImageEntity;

public interface ImageService {

    ImageEntity saveImage(MultipartFile file);

    byte[] downloadImage(int id);

    boolean deleteImage(int id);
}