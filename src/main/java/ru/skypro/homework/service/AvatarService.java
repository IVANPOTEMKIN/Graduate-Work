package ru.skypro.homework.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.AvatarEntity;

import java.io.IOException;
import java.nio.file.Path;

public interface AvatarService {

    AvatarEntity saveAvatar(MultipartFile file) throws IOException;

    AvatarEntity saveToDB(MultipartFile file,
                          Path path) throws IOException;

    ResponseEntity<byte[]> downloadFromDB(String path);
}