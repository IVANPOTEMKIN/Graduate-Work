package ru.skypro.homework.mapper;

import org.junit.jupiter.api.Test;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.mapper.impl.ImageMapperImpl;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static ru.skypro.homework.utils.Examples.createFilePNG;
import static ru.skypro.homework.utils.mapper.ImageMapperUtils.getImageEntity_From_MultipartFile;

class ImageMapperImplTest {

    private final ImageMapper mapper = new ImageMapperImpl();

    @Test
    void toImageEntity() throws IOException {
        ImageEntity expected = getImageEntity_From_MultipartFile();
        ImageEntity actual = mapper.toImageEntity(createFilePNG());

        assertNotNull(actual);
        assertEquals(expected, actual);
    }
}