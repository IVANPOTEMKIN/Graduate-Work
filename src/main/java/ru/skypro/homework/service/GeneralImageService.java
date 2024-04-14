package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

public interface GeneralImageService {

    Path savePath(MultipartFile file, String directory) throws IOException;
}