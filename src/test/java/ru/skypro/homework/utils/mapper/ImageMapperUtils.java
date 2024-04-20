package ru.skypro.homework.utils.mapper;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.ImageEntity;

import java.io.IOException;

import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

public class ImageMapperUtils {

    public static MultipartFile createFilePNG() {
        return new MockMultipartFile(
                "file",
                "image.png",
                IMAGE_PNG_VALUE,
                "bytes".getBytes()
        );
    }

    public static ImageEntity getImageEntity_From_MultipartFile() throws IOException {
        MultipartFile file = createFilePNG();
        ImageEntity entity = new ImageEntity();

        entity.setSize(file.getSize());
        entity.setType(file.getContentType());
        entity.setData(file.getBytes());

        return entity;
    }
}