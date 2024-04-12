package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.ImageEntity;
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

    @Value("${images.folder}")
    private String dir;

    @Override
    public ImageEntity saveFile(MultipartFile file) throws IOException {

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

        return saveImage(file, path);
    }

    private ImageEntity saveImage(MultipartFile file, Path path) {

        ImageEntity image = imageRepository.findImageEntityByFilePath(path.toString())
                .orElse(new ImageEntity());

        image.setFilePath(path.toString());
        image.setFileSize(file.getSize());
        image.setMediaType(file.getContentType());

        return imageRepository.save(image);
    }

    private Path createPath(MultipartFile file) {
        Random random = new Random();
        return Path.of(dir, file.getName().hashCode() * file.getSize() * random.nextInt(100)
                + "."
                + getExtensions(Objects.requireNonNull(file.getOriginalFilename())));
    }

    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}