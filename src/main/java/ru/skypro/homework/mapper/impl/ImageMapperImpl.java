package ru.skypro.homework.mapper.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.mapper.ImageMapper;

import java.io.IOException;

@Service
public class ImageMapperImpl implements ImageMapper {

    @Override
    public ImageEntity toImageEntity(MultipartFile file) {
        ImageEntity entity = new ImageEntity();

        try {
            entity.setSize(file.getSize());
            entity.setType(file.getContentType());
            entity.setData(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);//TODO исключение
        }

        return entity;
    }
}