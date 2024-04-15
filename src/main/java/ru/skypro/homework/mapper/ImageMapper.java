package ru.skypro.homework.mapper;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.ImageEntity;

public interface ImageMapper {

    ImageEntity toImageEntity(MultipartFile file);
}