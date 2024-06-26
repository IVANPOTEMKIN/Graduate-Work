package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.exception.ImageNotFoundException;
import ru.skypro.homework.exception.file.FailedSaveFileException;
import ru.skypro.homework.exception.file.FilePathNotFoundException;
import ru.skypro.homework.mapper.ImageMapper;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.ImageService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Random;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;

    @Value("${images.folder}")
    private String directory;

    @Override
    public ImageEntity saveImage(MultipartFile file) {
        Path path;

        try {
            path = savePath(file);
        } catch (IOException e) {
            throw new FailedSaveFileException();
        }

        return saveToDB(file, path);
    }

    @Override
    public byte[] downloadImage(int id) {
        ImageEntity image = getImage(id);
        Path path = Path.of(image.getPath());

        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            throw new FilePathNotFoundException();
        }
    }

    @Override
    public boolean deleteImage(int id) {
        ImageEntity image = getImage(id);
        Path path = Path.of(image.getPath());

        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new FilePathNotFoundException();
        }

        imageRepository.delete(image);
        return true;
    }

    /**
     * Сохранение изображения в БД
     *
     * @param file {@link MultipartFile}
     * @param path {@link Path}
     * @return {@link ImageEntity}
     */
    private ImageEntity saveToDB(MultipartFile file, Path path) {
        ImageEntity image = imageMapper.toImageEntity(file);
        image.setPath(path.toString());
        return imageRepository.save(image);
    }

    /**
     * Получение изображения по ID
     *
     * @param id <i> ID изображения </i>
     * @return {@link ImageEntity}
     */
    private ImageEntity getImage(int id) {
        return imageRepository.findById(id)
                .orElseThrow(ImageNotFoundException::new);
    }

    /**
     * Создание директории и сохранения файла локально
     *
     * @param file {@link MultipartFile}
     * @return {@link Path} <i> Путь сохранения файла локально </i>
     * @throws IOException <i> Возможное исключение при сохранении файла </i>
     */
    private Path savePath(MultipartFile file) throws IOException {
        Path path = createPath(file);

        Files.createDirectories(path.getParent());
        Files.deleteIfExists(path);

        try (
                InputStream is = file.getInputStream();
                OutputStream os = Files.newOutputStream(path, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024)) {

            bis.transferTo(bos);
        }

        return path;
    }

    /**
     * Генерация пути сохранения файла на основе директории и данных файла
     *
     * @param file {@link MultipartFile}
     * @return {@link Path} <i> Путь сохранения файла локально </i>
     */
    private Path createPath(MultipartFile file) {
        Random random = new Random();

        return Path.of(directory, file.getName().hashCode() * file.getSize() * random.nextInt(100)
                + "."
                + getExtensions(Objects.requireNonNull(file.getOriginalFilename())));
    }

    /**
     * Получение нового имени файла на основе оригинального
     *
     * @param fileName <i> Оригинальное имя файла</i>
     * @return {@link String} <i> Новое имя файла </i>
     */
    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}