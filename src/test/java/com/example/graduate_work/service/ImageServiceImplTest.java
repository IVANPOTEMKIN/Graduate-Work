package com.example.graduate_work.service;

import com.example.graduate_work.entity.ImageEntity;
import com.example.graduate_work.exception.ImageNotFoundException;
import com.example.graduate_work.exception.file.FailedRecordFileException;
import com.example.graduate_work.exception.file.FilePathNotFoundException;
import com.example.graduate_work.mapper.ImageMapper;
import com.example.graduate_work.repository.ImageRepository;
import com.example.graduate_work.service.impl.ImageServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static com.example.graduate_work.utils.Examples.createFilePNG;
import static com.example.graduate_work.utils.Examples.createImageEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImageServiceImplTest {

    @Mock
    private ImageRepository imageRepository;
    @Mock
    private ImageMapper imageMapper;
    @InjectMocks
    private ImageServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new ImageServiceImpl(imageRepository, imageMapper);
    }

    // saveImage

    @Test
    void saveImage_successful() {
        ImageEntity expected = createImageEntity();

        saveToDB(expected);

        ImageEntity actual = service.saveImage(createFilePNG());

        assertNotNull(actual);
        assertEquals(expected, actual);

        verify(imageMapper, times(1))
                .toImageEntity(any(MultipartFile.class));
        verify(imageRepository, times(1))
                .save(any(ImageEntity.class));
    }

    @Test
    void saveImage_FailedRecordFileException() {
        when(imageMapper.toImageEntity(any(MultipartFile.class)))
                .thenThrow(FailedRecordFileException.class);

        assertThrows(FailedRecordFileException.class,
                () -> service.saveImage(createFilePNG()));

        verify(imageMapper, times(1))
                .toImageEntity(any(MultipartFile.class));
        verify(imageRepository, times(0))
                .save(any(ImageEntity.class));
    }

    // downloadImage

    @Test
    void downloadImage_successful() {
        saveImage(createFilePNG(), createImageEntity());

        byte[] expected = createImageEntity().getData();
        byte[] actual = service.downloadImage(anyInt());

        assertNotNull(actual);
        assertArrayEquals(expected, actual);

        verify(imageRepository, times(1))
                .findById(anyInt());
    }

    @Test
    void downloadImage_ImageNotFoundException() {
        assertThrows(ImageNotFoundException.class,
                () -> service.downloadImage(anyInt()));

        verify(imageRepository, times(1))
                .findById(anyInt());
    }

    @Test
    void downloadImage_FilePathNotFoundException() {
        getImageById(createImageEntity());

        assertThrows(FilePathNotFoundException.class,
                () -> service.downloadImage(anyInt()));

        verify(imageRepository, times(1))
                .findById(anyInt());
    }

    // deleteImage

    @Test
    void deleteImage_successful() {
        saveImage(createFilePNG(), createImageEntity());

        assertTrue(service.deleteImage(anyInt()));
        assertThrows(FilePathNotFoundException.class,
                () -> service.deleteImage(anyInt()));
        assertThrows(FilePathNotFoundException.class,
                () -> service.downloadImage(anyInt()));

        verify(imageRepository, times(3))
                .findById(anyInt());
        verify(imageRepository, times(1))
                .delete(any(ImageEntity.class));
    }

    @Test
    void deleteImage_ImageNotFoundException() {
        assertThrows(ImageNotFoundException.class,
                () -> service.deleteImage(anyInt()));

        verify(imageRepository, times(1))
                .findById(anyInt());
        verify(imageRepository, times(0))
                .delete(any(ImageEntity.class));
    }

    @Test
    void deleteImage_FilePathNotFoundException() {
        getImageById(createImageEntity());

        assertThrows(FilePathNotFoundException.class,
                () -> service.deleteImage(anyInt()));

        verify(imageRepository, times(1))
                .findById(anyInt());
        verify(imageRepository, times(0))
                .delete(any(ImageEntity.class));
    }

    // Utils

    private void saveImage(MultipartFile file, ImageEntity entity) {
        saveToDB(entity);
        entity.setPath(service.saveImage(file).getPath());
        getImageById(entity);
    }

    private void saveToDB(ImageEntity entity) {
        when(imageMapper.toImageEntity(any(MultipartFile.class)))
                .thenReturn(entity);
        when(imageRepository.save(any(ImageEntity.class)))
                .thenReturn(entity);
    }

    private void getImageById(ImageEntity entity) {
        when(imageRepository.findById(anyInt()))
                .thenReturn(Optional.of(entity));
    }
}