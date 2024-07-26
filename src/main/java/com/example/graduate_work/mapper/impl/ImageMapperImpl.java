package com.example.graduate_work.mapper.impl;

import com.example.graduate_work.entity.ImageEntity;
import com.example.graduate_work.exception.file.FailedRecordFileException;
import com.example.graduate_work.mapper.ImageMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
            throw new FailedRecordFileException();
        }

        return entity;
    }
}