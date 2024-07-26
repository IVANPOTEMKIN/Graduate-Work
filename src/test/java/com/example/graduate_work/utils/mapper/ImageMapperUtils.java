package com.example.graduate_work.utils.mapper;

import com.example.graduate_work.entity.ImageEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.example.graduate_work.utils.Examples.createFilePNG;

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