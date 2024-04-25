package ru.skypro.homework.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ad.AdDTO;
import ru.skypro.homework.dto.ad.AdsDTO;
import ru.skypro.homework.dto.ad.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ad.ExtendedAdDTO;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.exception.ImageNotFoundException;
import ru.skypro.homework.exception.file.FailedRecordFileException;
import ru.skypro.homework.exception.file.FailedSaveFileException;
import ru.skypro.homework.exception.file.FilePathNotFoundException;
import ru.skypro.homework.exception.user.UserNotFoundException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.impl.AdServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static ru.skypro.homework.utils.Constants.PATH;
import static ru.skypro.homework.utils.Constants.USERNAME;
import static ru.skypro.homework.utils.Examples.*;

@ExtendWith({MockitoExtension.class, SpringExtension.class})
@ContextConfiguration
@WithMockUser(username = USERNAME, authorities = "ADMIN")
class AdServiceImplTest {

    @Mock
    private AdRepository adRepository;
    @Mock
    private UserService userService;
    @Mock
    private ImageService imageService;
    @Mock
    private AdMapper adMapper;
    @InjectMocks
    private AdServiceImpl adService;

    @BeforeEach
    void setUp() {
        adService = new AdServiceImpl(adRepository, userService, imageService, adMapper);
    }

    @Test
    void getAllAds() {
        when(adRepository.findAll())
                .thenReturn(List.of(createAdEntity()));

        mapToAdDTO();

        AdsDTO expected = createAdsDTO();
        AdsDTO actual = adService.getAllAds();

        assertNotNull(actual);
        assertEquals(expected, actual);

        verify(adRepository, times(1))
                .findAll();
        verify(adMapper, times(1))
                .toAdDTO(any(AdEntity.class));
    }

    // addAd

    @Test
    void addAd_successful() {
        mapToAdEntity();
        getUser();
        getImage();
        mapToAdDTO();

        AdDTO expected = createAdDTO();
        AdDTO actual = adService.addAd(createCreateOrUpdateAdDTO(), createFilePNG());

        assertNotNull(actual);
        assertEquals(expected, actual);

        verify(adMapper, times(1))
                .toAdEntity(any(CreateOrUpdateAdDTO.class));
        verify(userService, times(1))
                .getUser();
        verify(imageService, times(1))
                .saveImage(any(MultipartFile.class));
        verify(adRepository, times(1))
                .save(any(AdEntity.class));
        verify(adMapper, times(1))
                .toAdDTO(any(AdEntity.class));
    }

    @Test
    void addAd_UserNotFoundException() {
        mapToAdEntity();
        getUser_UserNotFoundException();

        assertThrows(UserNotFoundException.class,
                () -> adService.addAd(createCreateOrUpdateAdDTO(), createFilePNG()));

        verify(adMapper, times(1))
                .toAdEntity(any(CreateOrUpdateAdDTO.class));
        verify(userService, times(1))
                .getUser();
        verify(imageService, times(0))
                .saveImage(any(MultipartFile.class));
        verify(adRepository, times(0))
                .save(any(AdEntity.class));
        verify(adMapper, times(0))
                .toAdDTO(any(AdEntity.class));
    }

    @Test
    void addAd_FailedSaveFileException() {
        mapToAdEntity();
        getUser();
        getImage_FailedSaveFileException();

        assertThrows(FailedSaveFileException.class,
                () -> adService.addAd(createCreateOrUpdateAdDTO(), createFilePNG()));

        verify(adMapper, times(1))
                .toAdEntity(any(CreateOrUpdateAdDTO.class));
        verify(userService, times(1))
                .getUser();
        verify(imageService, times(1))
                .saveImage(any(MultipartFile.class));
        verify(adRepository, times(0))
                .save(any(AdEntity.class));
        verify(adMapper, times(0))
                .toAdDTO(any(AdEntity.class));
    }

    @Test
    void addAd_FailedRecordFileException() {
        mapToAdEntity();
        getUser();
        getImage_FailedRecordFileException();

        assertThrows(FailedRecordFileException.class,
                () -> adService.addAd(createCreateOrUpdateAdDTO(), createFilePNG()));

        verify(adMapper, times(1))
                .toAdEntity(any(CreateOrUpdateAdDTO.class));
        verify(userService, times(1))
                .getUser();
        verify(imageService, times(1))
                .saveImage(any(MultipartFile.class));
        verify(adRepository, times(0))
                .save(any(AdEntity.class));
        verify(adMapper, times(0))
                .toAdDTO(any(AdEntity.class));
    }

    // getInfoAboutAd

    @Test
    void getInfoAboutAd_successful() {
        getAdById();

        when(adMapper.toExtendedAdDTO(any(AdEntity.class)))
                .thenReturn(createExtendedAdDTO());

        ExtendedAdDTO expected = createExtendedAdDTO();
        ExtendedAdDTO actual = adService.getInfoAboutAd(anyInt());

        assertNotNull(actual);
        assertEquals(expected, actual);

        verify(adRepository, times(1))
                .findById(anyInt());
        verify(adMapper, times(1))
                .toExtendedAdDTO(any(AdEntity.class));

    }

    @Test
    void getInfoAboutAd_AdNotFoundException() {
        assertThrows(AdNotFoundException.class,
                () -> adService.getInfoAboutAd(anyInt()));

        verify(adRepository, times(1))
                .findById(anyInt());
        verify(adMapper, times(0))
                .toExtendedAdDTO(any(AdEntity.class));
    }

    // deleteAd

    @Test
    void deleteAd_successful() {
        getAdById();

        when(imageService.deleteImage(anyInt()))
                .thenReturn(true);

        assertTrue(adService.deleteAd(anyInt()));

        verify(adRepository, times(1))
                .findById(anyInt());
        verify(adRepository, times(1))
                .delete(any(AdEntity.class));
        verify(imageService, times(1))
                .deleteImage(anyInt());
    }

    @Test
    void deleteAd_AdNotFoundException() {
        assertThrows(AdNotFoundException.class,
                () -> adService.deleteAd(anyInt()));

        verify(adRepository, times(1))
                .findById(anyInt());
        verify(adRepository, times(0))
                .delete(any(AdEntity.class));
        verify(imageService, times(0))
                .deleteImage(anyInt());
    }

    @Test
    void deleteAd_ImageNotFoundException() {
        getAdById();

        when(imageService.deleteImage(anyInt()))
                .thenThrow(ImageNotFoundException.class);

        assertThrows(ImageNotFoundException.class,
                () -> adService.deleteAd(anyInt()));

        verify(adRepository, times(1))
                .findById(anyInt());
        verify(adRepository, times(1))
                .delete(any(AdEntity.class));
        verify(imageService, times(1))
                .deleteImage(anyInt());
    }

    @Test
    void deleteAd_FilePathNotFoundException() {
        getAdById();

        when(imageService.deleteImage(anyInt()))
                .thenThrow(FilePathNotFoundException.class);

        assertThrows(FilePathNotFoundException.class,
                () -> adService.deleteAd(anyInt()));

        verify(adRepository, times(1))
                .findById(anyInt());
        verify(adRepository, times(1))
                .delete(any(AdEntity.class));
        verify(imageService, times(1))
                .deleteImage(anyInt());
    }

    // updateInfoAboutAd

    @Test
    void updateInfoAboutAd_successful() {
        getAdById();
        mapToAdDTO();

        AdDTO expected = createAdDTO();
        AdDTO actual = adService.updateInfoAboutAd(anyInt(), createCreateOrUpdateAdDTO());

        assertNotNull(actual);
        assertEquals(expected, actual);

        verify(adRepository, times(1))
                .findById(anyInt());
        verify(adRepository, times(1))
                .save(any(AdEntity.class));
        verify(adMapper, times(1))
                .toAdDTO(any(AdEntity.class));
    }

    @Test
    void updateInfoAboutAd_AdNotFoundException() {
        assertThrows(AdNotFoundException.class,
                () -> adService.updateInfoAboutAd(anyInt(), createCreateOrUpdateAdDTO()));

        verify(adRepository, times(1))
                .findById(anyInt());
        verify(adRepository, times(0))
                .save(any(AdEntity.class));
        verify(adMapper, times(0))
                .toAdDTO(any(AdEntity.class));
    }

    // getAllAdsOfUser

    @Test
    void getAllAdsOfUser_successful() {
        getUser();

        when(adRepository.findAdEntitiesByAuthor(any(UserEntity.class)))
                .thenReturn(List.of(createAdEntity()));

        mapToAdDTO();

        AdsDTO expected = createAdsDTO();
        AdsDTO actual = adService.getAllAdsOfUser();

        assertNotNull(actual);
        assertEquals(expected, actual);

        verify(userService, times(1))
                .getUser();
        verify(adRepository, times(1))
                .findAdEntitiesByAuthor(any(UserEntity.class));
        verify(adMapper, times(1))
                .toAdDTO(any(AdEntity.class));
    }

    @Test
    void getAllAdsOfUser_UserNotFoundException() {
        getUser_UserNotFoundException();

        assertThrows(UserNotFoundException.class,
                () -> adService.getAllAdsOfUser());

        verify(userService, times(1))
                .getUser();
        verify(adRepository, times(0))
                .findAdEntitiesByAuthor(any(UserEntity.class));
        verify(adMapper, times(0))
                .toAdDTO(any(AdEntity.class));
    }

    // updateImageOfAd

    @Test
    void updateImageOfAd_successful() {
        getAdById();
        getImage();

        String actual = adService.updateImageOfAd(anyInt(), createFilePNG());

        assertNotNull(actual);
        assertEquals(PATH, actual);

        verify(adRepository, times(1))
                .findById(anyInt());
        verify(imageService, times(1))
                .saveImage(any(MultipartFile.class));
        verify(adRepository, times(1))
                .save(any(AdEntity.class));
    }

    @Test
    void updateImageOfAd_AdNotFoundException() {
        assertThrows(AdNotFoundException.class,
                () -> adService.updateImageOfAd(anyInt(), createFilePNG()));

        verify(adRepository, times(1))
                .findById(anyInt());
        verify(imageService, times(0))
                .saveImage(any(MultipartFile.class));
        verify(adRepository, times(0))
                .save(any(AdEntity.class));
    }

    @Test
    void updateImageOfAd_FailedSaveFileException() {
        getAdById();
        getImage_FailedSaveFileException();

        assertThrows(FailedSaveFileException.class,
                () -> adService.updateImageOfAd(anyInt(), createFilePNG()));

        verify(adRepository, times(1))
                .findById(anyInt());
        verify(imageService, times(1))
                .saveImage(any(MultipartFile.class));
        verify(adRepository, times(0))
                .save(any(AdEntity.class));
    }

    @Test
    void updateImageOfAd_FailedRecordFileException() {
        getAdById();
        getImage_FailedRecordFileException();

        assertThrows(FailedRecordFileException.class,
                () -> adService.updateImageOfAd(anyInt(), createFilePNG()));

        verify(adRepository, times(1))
                .findById(anyInt());
        verify(imageService, times(1))
                .saveImage(any(MultipartFile.class));
        verify(adRepository, times(0))
                .save(any(AdEntity.class));
    }

    // getById

    @Test
    void getById_successful() {
        getAdById();

        AdEntity expected = createAdEntity();
        AdEntity actual = adService.getById(anyInt());

        assertNotNull(actual);
        assertEquals(expected, actual);

        verify(adRepository, times(1))
                .findById(anyInt());
    }

    @Test
    void getById_AdNotFoundException() {
        assertThrows(AdNotFoundException.class,
                () -> adService.getById(anyInt()));

        verify(adRepository, times(1))
                .findById(anyInt());
    }

    // Utils

    private void getAdById() {
        when(adRepository.findById(anyInt()))
                .thenReturn(Optional.of(createAdEntity()));
    }

    private void getUser() {
        when(userService.getUser())
                .thenReturn(createUserEntity());
    }

    private void getImage() {
        when(imageService.saveImage(any(MultipartFile.class)))
                .thenReturn(createImageEntity());
    }

    private void getUser_UserNotFoundException() {
        when(userService.getUser())
                .thenThrow(UserNotFoundException.class);
    }

    private void getImage_FailedSaveFileException() {
        when(imageService.saveImage(any(MultipartFile.class)))
                .thenThrow(FailedSaveFileException.class);
    }

    private void getImage_FailedRecordFileException() {
        when(imageService.saveImage(any(MultipartFile.class)))
                .thenThrow(FailedRecordFileException.class);
    }

    private void mapToAdDTO() {
        when(adMapper.toAdDTO(any(AdEntity.class)))
                .thenReturn(createAdDTO());
    }

    private void mapToAdEntity() {
        when(adMapper.toAdEntity(any(CreateOrUpdateAdDTO.class)))
                .thenReturn(createAdEntity());
    }
}