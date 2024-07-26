package com.example.graduate_work.mapper;

import com.example.graduate_work.entity.ImageEntity;
import com.example.graduate_work.mapper.impl.ImageMapperImpl;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.example.graduate_work.utils.Examples.createFilePNG;
import static com.example.graduate_work.utils.mapper.ImageMapperUtils.getImageEntity_From_MultipartFile;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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