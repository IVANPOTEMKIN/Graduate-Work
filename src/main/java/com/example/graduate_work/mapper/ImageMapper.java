package com.example.graduate_work.mapper;

import com.example.graduate_work.entity.ImageEntity;
import org.springframework.web.multipart.MultipartFile;

public interface ImageMapper {

    ImageEntity toImageEntity(MultipartFile file);
}