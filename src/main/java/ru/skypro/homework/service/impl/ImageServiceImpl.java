package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.exception.file.FailedSaveFileException;
import ru.skypro.homework.exception.file.FilePathNotFoundException;
import ru.skypro.homework.exception.ImageNotFoundException;
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

    private final ImageRepository repository;
    private final ImageMapper mapper;

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
    public ResponseEntity<byte[]> downloadImage(int id) {
        ImageEntity image = getImage(id);
        Path path = Path.of(image.getPath());

        try {
            return ResponseEntity.ok(Files.readAllBytes(path));
        } catch (IOException e) {
            throw new FilePathNotFoundException();
        }
    }

    @Override
    public void deleteImage(int id) {
        ImageEntity image = getImage(id);
        Path path = Path.of(image.getPath());

        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new FilePathNotFoundException();
        }

        repository.delete(image);
    }

    private ImageEntity saveToDB(MultipartFile file, Path path) {
        ImageEntity image = mapper.toImageEntity(file);
        image.setPath(path.toString());
        return repository.save(image);
    }

    private ImageEntity getImage(int id) {
        return repository.findImageEntityById(id)
                .orElseThrow(ImageNotFoundException::new);
    }

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

    private Path createPath(MultipartFile file) {

        Random random = new Random();

        return Path.of(directory, file.getName().hashCode() * file.getSize() * random.nextInt(100)
                + "."
                + getExtensions(Objects.requireNonNull(file.getOriginalFilename())));
    }

    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}