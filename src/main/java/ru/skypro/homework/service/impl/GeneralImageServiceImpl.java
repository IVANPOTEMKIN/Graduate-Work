package ru.skypro.homework.service.impl;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.service.GeneralImageService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Random;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

public class GeneralImageServiceImpl implements GeneralImageService {

    @Override
    public Path savePath(MultipartFile file,
                         String directory) throws IOException {

        Path path = createPath(file, directory);

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

    private static Path createPath(MultipartFile file,
                                   String directory) {

        Random random = new Random();

        return Path.of(directory, file.getName().hashCode() * file.getSize() * random.nextInt(100)
                + "."
                + getExtensions(Objects.requireNonNull(file.getOriginalFilename())));
    }

    private static String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}