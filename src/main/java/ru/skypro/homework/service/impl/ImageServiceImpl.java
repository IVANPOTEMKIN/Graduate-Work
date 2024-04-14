package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.ImageService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl extends GeneralImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Value("${images.folder}")
    private String directory;

    @Override
    public ImageEntity saveImage(MultipartFile file) throws IOException {
        Path path = savePath(file, directory);
        return saveToDB(file, path);
    }

    @Override
    public ImageEntity saveToDB(MultipartFile file,
                                Path path) throws IOException {

        ImageEntity image = imageRepository.findImageEntityByFilePath(path.toString())
                .orElse(new ImageEntity());

        image.setFilePath(path.toString());
        image.setFileSize(file.getSize());
        image.setMediaType(file.getContentType());
        image.setData(file.getBytes());

        return imageRepository.save(image);
    }

    @Override
    public void downloadFromDirectory(HttpServletResponse response,
                                      String path) throws IOException {

        ImageEntity image = getImage(path);
        Path from = Path.of(image.getFilePath());

        try (InputStream is = Files.newInputStream(from);
             OutputStream os = response.getOutputStream()) {
            response.setStatus(200);
            response.setContentType(image.getMediaType());
            response.setContentLength(image.getFileSize().intValue());
            is.transferTo(os);
        }
    }

    private ImageEntity getImage(String path) {
        return imageRepository.findImageEntityByFilePath(path).orElseThrow();
    }
}