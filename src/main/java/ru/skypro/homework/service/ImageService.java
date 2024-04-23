package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.ImageEntity;

public interface ImageService {

    /**
     * Сохранение изображение локально и в БД
     *
     * @param file {@link MultipartFile}
     * @return {@link ImageEntity}
     */
    ImageEntity saveImage(MultipartFile file);

    /**
     * Получение изображения из директории
     *
     * @param id <i> ID изображения </i>
     * @return {@link Byte}[] <code> Image.getBytes() </code>
     */
    byte[] downloadImage(int id);

    /**
     * Удаление изображения из директории и из БД
     *
     * @param id <i> ID изображения </i>
     * @return {@link Boolean} <i> Результат выполнения метода </i>
     */
    boolean deleteImage(int id);
}