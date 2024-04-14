package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.AvatarEntity;
import ru.skypro.homework.repository.AvatarRepository;
import ru.skypro.homework.service.AvatarService;

import java.io.IOException;
import java.nio.file.Path;

@Service
@RequiredArgsConstructor
public class AvatarServiceImpl extends GeneralImageServiceImpl implements AvatarService {

    private final AvatarRepository avatarRepository;

    @Value("${avatars.folder}")
    private String dir;

    @Override
    public AvatarEntity saveAvatar(MultipartFile file) throws IOException {
        Path path = savePath(file, dir);
        return saveToDB(file, path);
    }

    @Override
    public AvatarEntity saveToDB(MultipartFile file,
                                 Path path) throws IOException {

        AvatarEntity avatar = avatarRepository.findAvatarEntityByFilePath(path.toString())
                .orElse(new AvatarEntity());

        avatar.setFilePath(path.toString());
        avatar.setFileSize(file.getSize());
        avatar.setMediaType(file.getContentType());
        avatar.setData(file.getBytes());

        return avatarRepository.save(avatar);
    }

    @Override
    public ResponseEntity<byte[]> downloadFromDB(String path) {
        AvatarEntity avatar = getAvatar(path);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        headers.setContentLength(avatar.getData().length);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatar.getData());
    }

    private AvatarEntity getAvatar(String path) {
        return avatarRepository.findAvatarEntityByFilePath(path).orElseThrow();
    }
}