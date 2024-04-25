package ru.skypro.homework.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.exception.ImageNotFoundException;
import ru.skypro.homework.exception.file.FailedRecordFileException;
import ru.skypro.homework.exception.file.FilePathNotFoundException;
import ru.skypro.homework.mapper.ImageMapper;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.impl.ImageServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static ru.skypro.homework.utils.Examples.createFilePNG;
import static ru.skypro.homework.utils.Examples.createImageEntity;

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