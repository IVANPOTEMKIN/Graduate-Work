package ru.skypro.homework.utils.mapper;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.ImageEntity;

import java.io.IOException;

import static ru.skypro.homework.utils.Examples.createFilePNG;

public class ImageMapperUtils {

    public static ImageEntity getImageEntity_From_MultipartFile() throws IOException {
        MultipartFile file = createFilePNG();
        ImageEntity entity = new ImageEntity();

        entity.setSize(file.getSize());
        entity.setType(file.getContentType());
        entity.setData(file.getBytes());

        return entity;
    }
}