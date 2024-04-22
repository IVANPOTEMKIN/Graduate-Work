package ru.skypro.homework.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.auth.NewPasswordDTO;
import ru.skypro.homework.dto.user.UpdateUserDTO;
import ru.skypro.homework.dto.user.UserDTO;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.file.FailedRecordFileException;
import ru.skypro.homework.exception.file.FailedSaveFileException;
import ru.skypro.homework.exception.password.ReusePasswordException;
import ru.skypro.homework.exception.password.WrongPasswordException;
import ru.skypro.homework.exception.user.UserNotFoundException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.impl.UserServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static ru.skypro.homework.utils.Constants.USERNAME;
import static ru.skypro.homework.utils.Examples.*;

@ExtendWith({MockitoExtension.class, SpringExtension.class})
@ContextConfiguration
@WithMockUser(username = USERNAME, authorities = "ADMIN")
class UserServiceImplTest {

    @Mock
    private PasswordEncoder encoder;
    @Mock
    private UserRepository repository;
    @Mock
    private ImageService imageService;
    @Mock
    private UserMapper mapper;
    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(encoder, repository, imageService, mapper);
    }

    @Test
    void updatePassword_successful() {
        getUserByUsername_successful();

        UserEntity user = createUserEntity();
        NewPasswordDTO dto = createNewPasswordDTO();

        when(encoder.matches(dto.getCurrentPassword(), user.getPassword()))
                .thenReturn(true);
        when(encoder.matches(dto.getNewPassword(), user.getPassword()))
                .thenReturn(false);

        assertTrue(userService.updatePassword(dto));

        verify(repository, times(1))
                .findUserEntityByUsername(anyString());
        verify(encoder, times(1))
                .matches(dto.getCurrentPassword(), user.getPassword());
        verify(encoder, times(1))
                .matches(dto.getNewPassword(), user.getPassword());
        verify(encoder, times(1))
                .encode(anyString());
        verify(repository, times(1))
                .save(any(UserEntity.class));
    }

    @Test
    void updatePassword_UserNotFoundException() {
        assertThrows(UserNotFoundException.class,
                () -> userService.updatePassword(createNewPasswordDTO()));

        verify(repository, times(1))
                .findUserEntityByUsername(anyString());
        verify(encoder, times(0))
                .matches(anyString(), anyString());
        verify(encoder, times(0))
                .encode(anyString());
        verify(repository, times(0))
                .save(any(UserEntity.class));
    }

    @Test
    void updatePassword_WrongPasswordException() {
        getUserByUsername_successful();

        UserEntity user = createUserEntity();
        NewPasswordDTO dto = createNewPasswordDTO();

        when(encoder.matches(dto.getCurrentPassword(), user.getPassword()))
                .thenThrow(WrongPasswordException.class);

        assertThrows(WrongPasswordException.class,
                () -> userService.updatePassword(dto));

        verify(repository, times(1))
                .findUserEntityByUsername(anyString());
        verify(encoder, times(1))
                .matches(dto.getCurrentPassword(), user.getPassword());
        verify(encoder, times(0))
                .matches(dto.getNewPassword(), user.getPassword());
        verify(encoder, times(0))
                .encode(anyString());
        verify(repository, times(0))
                .save(any(UserEntity.class));
    }

    @Test
    void updatePassword_ReusePasswordException() {
        getUserByUsername_successful();

        UserEntity user = createUserEntity();
        NewPasswordDTO dto = createNewPasswordDTO();

        when(encoder.matches(dto.getCurrentPassword(), user.getPassword()))
                .thenReturn(true);
        when(encoder.matches(dto.getNewPassword(), user.getPassword()))
                .thenThrow(ReusePasswordException.class);

        assertThrows(ReusePasswordException.class,
                () -> userService.updatePassword(dto));

        verify(repository, times(1))
                .findUserEntityByUsername(anyString());
        verify(encoder, times(1))
                .matches(dto.getCurrentPassword(), user.getPassword());
        verify(encoder, times(1))
                .matches(dto.getNewPassword(), user.getPassword());
        verify(encoder, times(0))
                .encode(anyString());
        verify(repository, times(0))
                .save(any(UserEntity.class));
    }

    @Test
    void getInfoAboutUser_successful() {
        getUserByUsername_successful();

        when(mapper.toUserDTO(any(UserEntity.class)))
                .thenReturn(createUserDTO());

        UserDTO expected = createUserDTO();
        UserDTO actual = userService.getInfoAboutUser();

        assertNotNull(actual);
        assertEquals(expected, actual);

        verify(repository, times(1))
                .findUserEntityByUsername(anyString());
        verify(mapper, times(1))
                .toUserDTO(any(UserEntity.class));
    }

    @Test
    void getInfoAboutUser_UserNotFoundException() {
        assertThrows(UserNotFoundException.class,
                () -> userService.getInfoAboutUser());

        verify(repository, times(1))
                .findUserEntityByUsername(anyString());
        verify(mapper, times(0))
                .toUserDTO(any(UserEntity.class));
    }

    @Test
    void updateInfoAboutUser_successful() {
        getUserByUsername_successful();

        when(mapper.toUpdateUserDTO(any(UserEntity.class)))
                .thenReturn(createUpdateUserDTO());

        UpdateUserDTO expected = createUpdateUserDTO();
        UpdateUserDTO actual = userService.updateInfoAboutUser(createUpdateUserDTO());

        assertNotNull(actual);
        assertEquals(expected, actual);

        verify(repository, times(1))
                .findUserEntityByUsername(anyString());
        verify(repository, times(1))
                .save(any(UserEntity.class));
        verify(mapper, times(1))
                .toUpdateUserDTO(any(UserEntity.class));
    }

    @Test
    void updateInfoAboutUser_UserNotFoundException() {
        assertThrows(UserNotFoundException.class,
                () -> userService.updateInfoAboutUser(createUpdateUserDTO()));

        verify(repository, times(1))
                .findUserEntityByUsername(anyString());
        verify(repository, times(0))
                .save(any(UserEntity.class));
        verify(mapper, times(0))
                .toUpdateUserDTO(any(UserEntity.class));
    }

    @Test
    void updateAvatarOfUser_successful() {
        getUserByUsername_successful();

        MultipartFile file = createFilePNG();

        when(imageService.saveImage(any(MultipartFile.class)))
                .thenReturn(createImageEntity());

        assertTrue(userService.updateAvatarOfUser(file));

        verify(repository, times(1))
                .findUserEntityByUsername(anyString());
        verify(imageService, times(1))
                .saveImage(any(MultipartFile.class));
        verify(repository, times(1))
                .save(any(UserEntity.class));
    }

    @Test
    void updateAvatarOfUser_UserNotFoundException() {
        assertThrows(UserNotFoundException.class,
                () -> userService.updateAvatarOfUser(createFilePNG()));

        verify(repository, times(1))
                .findUserEntityByUsername(anyString());
        verify(imageService, times(0))
                .saveImage(any(MultipartFile.class));
        verify(repository, times(0))
                .save(any(UserEntity.class));
    }

    @Test
    void updateAvatarOfUser_FailedSaveFileException() {
        getUserByUsername_successful();

        MultipartFile file = createFilePNG();

        when(imageService.saveImage(any(MultipartFile.class)))
                .thenThrow(FailedSaveFileException.class);

        assertThrows(FailedSaveFileException.class,
                () -> userService.updateAvatarOfUser(file));

        verify(repository, times(1))
                .findUserEntityByUsername(anyString());
        verify(imageService, times(1))
                .saveImage(any(MultipartFile.class));
        verify(repository, times(0))
                .save(any(UserEntity.class));
    }

    @Test
    void updateAvatarOfUser_FailedRecordFileException() {
        getUserByUsername_successful();

        MultipartFile file = createFilePNG();

        when(imageService.saveImage(any(MultipartFile.class)))
                .thenThrow(FailedRecordFileException.class);

        assertThrows(FailedRecordFileException.class,
                () -> userService.updateAvatarOfUser(file));

        verify(repository, times(1))
                .findUserEntityByUsername(anyString());
        verify(imageService, times(1))
                .saveImage(any(MultipartFile.class));
        verify(repository, times(0))
                .save(any(UserEntity.class));
    }

    @Test
    void getUser_successful() {
        getUserByUsername_successful();

        UserEntity expected = createUserEntity();
        UserEntity actual = userService.getUser();

        assertNotNull(actual);
        assertEquals(expected, actual);

        verify(repository, times(1))
                .findUserEntityByUsername(anyString());
    }

    @Test
    void getUser_UserNotFoundException() {
        assertThrows(UserNotFoundException.class,
                () -> userService.getUser());

        verify(repository, times(1))
                .findUserEntityByUsername(anyString());
    }

    private void getUserByUsername_successful() {
        when(repository.findUserEntityByUsername(anyString()))
                .thenReturn(Optional.of(createUserEntity()));
    }
}