package com.example.graduate_work.service;

import com.example.graduate_work.dto.auth.NewPasswordDTO;
import com.example.graduate_work.dto.user.UpdateUserDTO;
import com.example.graduate_work.dto.user.UserDTO;
import com.example.graduate_work.entity.UserEntity;
import com.example.graduate_work.exception.file.FailedRecordFileException;
import com.example.graduate_work.exception.file.FailedSaveFileException;
import com.example.graduate_work.exception.password.ReusePasswordException;
import com.example.graduate_work.exception.password.WrongPasswordException;
import com.example.graduate_work.exception.user.UserNotFoundException;
import com.example.graduate_work.mapper.UserMapper;
import com.example.graduate_work.repository.UserRepository;
import com.example.graduate_work.service.impl.UserServiceImpl;
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

import java.util.Optional;

import static com.example.graduate_work.utils.Constants.USERNAME;
import static com.example.graduate_work.utils.Examples.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class, SpringExtension.class})
@ContextConfiguration
@WithMockUser(username = USERNAME, authorities = "ADMIN")
class UserServiceImplTest {

    @Mock
    private PasswordEncoder encoder;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ImageService imageService;
    @Mock
    private UserMapper userMapper;
    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(encoder, userRepository, imageService, userMapper);
    }

    // updatePassword

    @Test
    void updatePassword_successful() {
        getUserByUsername();

        UserEntity user = createUserEntity();
        NewPasswordDTO dto = createNewPasswordDTO();

        when(encoder.matches(dto.getCurrentPassword(), user.getPassword()))
                .thenReturn(true);
        when(encoder.matches(dto.getNewPassword(), user.getPassword()))
                .thenReturn(false);

        assertTrue(userService.updatePassword(dto));

        verify(userRepository, times(1))
                .findUserEntityByUsername(anyString());
        verify(encoder, times(1))
                .matches(dto.getCurrentPassword(), user.getPassword());
        verify(encoder, times(1))
                .matches(dto.getNewPassword(), user.getPassword());
        verify(encoder, times(1))
                .encode(anyString());
        verify(userRepository, times(1))
                .save(any(UserEntity.class));
    }

    @Test
    void updatePassword_UserNotFoundException() {
        assertThrows(UserNotFoundException.class,
                () -> userService.updatePassword(createNewPasswordDTO()));

        verify(userRepository, times(1))
                .findUserEntityByUsername(anyString());
        verify(encoder, times(0))
                .matches(anyString(), anyString());
        verify(encoder, times(0))
                .encode(anyString());
        verify(userRepository, times(0))
                .save(any(UserEntity.class));
    }

    @Test
    void updatePassword_WrongPasswordException() {
        getUserByUsername();

        UserEntity user = createUserEntity();
        NewPasswordDTO dto = createNewPasswordDTO();

        when(encoder.matches(dto.getCurrentPassword(), user.getPassword()))
                .thenThrow(WrongPasswordException.class);

        assertThrows(WrongPasswordException.class,
                () -> userService.updatePassword(dto));

        verify(userRepository, times(1))
                .findUserEntityByUsername(anyString());
        verify(encoder, times(1))
                .matches(dto.getCurrentPassword(), user.getPassword());
        verify(encoder, times(0))
                .matches(dto.getNewPassword(), user.getPassword());
        verify(encoder, times(0))
                .encode(anyString());
        verify(userRepository, times(0))
                .save(any(UserEntity.class));
    }

    @Test
    void updatePassword_ReusePasswordException() {
        getUserByUsername();

        UserEntity user = createUserEntity();
        NewPasswordDTO dto = createNewPasswordDTO();

        when(encoder.matches(dto.getCurrentPassword(), user.getPassword()))
                .thenReturn(true);
        when(encoder.matches(dto.getNewPassword(), user.getPassword()))
                .thenThrow(ReusePasswordException.class);

        assertThrows(ReusePasswordException.class,
                () -> userService.updatePassword(dto));

        verify(userRepository, times(1))
                .findUserEntityByUsername(anyString());
        verify(encoder, times(1))
                .matches(dto.getCurrentPassword(), user.getPassword());
        verify(encoder, times(1))
                .matches(dto.getNewPassword(), user.getPassword());
        verify(encoder, times(0))
                .encode(anyString());
        verify(userRepository, times(0))
                .save(any(UserEntity.class));
    }

    // getInfoAboutUser

    @Test
    void getInfoAboutUser_successful() {
        getUserByUsername();

        when(userMapper.toUserDTO(any(UserEntity.class)))
                .thenReturn(createUserDTO());

        UserDTO expected = createUserDTO();
        UserDTO actual = userService.getInfoAboutUser();

        assertNotNull(actual);
        assertEquals(expected, actual);

        verify(userRepository, times(1))
                .findUserEntityByUsername(anyString());
        verify(userMapper, times(1))
                .toUserDTO(any(UserEntity.class));
    }

    @Test
    void getInfoAboutUser_UserNotFoundException() {
        assertThrows(UserNotFoundException.class,
                () -> userService.getInfoAboutUser());

        verify(userRepository, times(1))
                .findUserEntityByUsername(anyString());
        verify(userMapper, times(0))
                .toUserDTO(any(UserEntity.class));
    }

    // updateInfoAboutUser

    @Test
    void updateInfoAboutUser_successful() {
        getUserByUsername();

        when(userMapper.toUpdateUserDTO(any(UserEntity.class)))
                .thenReturn(createUpdateUserDTO());

        UpdateUserDTO expected = createUpdateUserDTO();
        UpdateUserDTO actual = userService.updateInfoAboutUser(createUpdateUserDTO());

        assertNotNull(actual);
        assertEquals(expected, actual);

        verify(userRepository, times(1))
                .findUserEntityByUsername(anyString());
        verify(userRepository, times(1))
                .save(any(UserEntity.class));
        verify(userMapper, times(1))
                .toUpdateUserDTO(any(UserEntity.class));
    }

    @Test
    void updateInfoAboutUser_UserNotFoundException() {
        assertThrows(UserNotFoundException.class,
                () -> userService.updateInfoAboutUser(createUpdateUserDTO()));

        verify(userRepository, times(1))
                .findUserEntityByUsername(anyString());
        verify(userRepository, times(0))
                .save(any(UserEntity.class));
        verify(userMapper, times(0))
                .toUpdateUserDTO(any(UserEntity.class));
    }

    // updateAvatarOfUser

    @Test
    void updateAvatarOfUser_successful() {
        getUserByUsername();

        MultipartFile file = createFilePNG();

        when(imageService.saveImage(any(MultipartFile.class)))
                .thenReturn(createImageEntity());

        assertTrue(userService.updateAvatarOfUser(file));

        verify(userRepository, times(1))
                .findUserEntityByUsername(anyString());
        verify(imageService, times(1))
                .saveImage(any(MultipartFile.class));
        verify(userRepository, times(1))
                .save(any(UserEntity.class));
    }

    @Test
    void updateAvatarOfUser_UserNotFoundException() {
        assertThrows(UserNotFoundException.class,
                () -> userService.updateAvatarOfUser(createFilePNG()));

        verify(userRepository, times(1))
                .findUserEntityByUsername(anyString());
        verify(imageService, times(0))
                .saveImage(any(MultipartFile.class));
        verify(userRepository, times(0))
                .save(any(UserEntity.class));
    }

    @Test
    void updateAvatarOfUser_FailedSaveFileException() {
        getUserByUsername();

        MultipartFile file = createFilePNG();

        when(imageService.saveImage(any(MultipartFile.class)))
                .thenThrow(FailedSaveFileException.class);

        assertThrows(FailedSaveFileException.class,
                () -> userService.updateAvatarOfUser(file));

        verify(userRepository, times(1))
                .findUserEntityByUsername(anyString());
        verify(imageService, times(1))
                .saveImage(any(MultipartFile.class));
        verify(userRepository, times(0))
                .save(any(UserEntity.class));
    }

    @Test
    void updateAvatarOfUser_FailedRecordFileException() {
        getUserByUsername();

        MultipartFile file = createFilePNG();

        when(imageService.saveImage(any(MultipartFile.class)))
                .thenThrow(FailedRecordFileException.class);

        assertThrows(FailedRecordFileException.class,
                () -> userService.updateAvatarOfUser(file));

        verify(userRepository, times(1))
                .findUserEntityByUsername(anyString());
        verify(imageService, times(1))
                .saveImage(any(MultipartFile.class));
        verify(userRepository, times(0))
                .save(any(UserEntity.class));
    }

    // getUser

    @Test
    void getUser_successful() {
        getUserByUsername();

        UserEntity expected = createUserEntity();
        UserEntity actual = userService.getUser();

        assertNotNull(actual);
        assertEquals(expected, actual);

        verify(userRepository, times(1))
                .findUserEntityByUsername(anyString());
    }

    @Test
    void getUser_UserNotFoundException() {
        assertThrows(UserNotFoundException.class,
                () -> userService.getUser());

        verify(userRepository, times(1))
                .findUserEntityByUsername(anyString());
    }

    private void getUserByUsername() {
        when(userRepository.findUserEntityByUsername(anyString()))
                .thenReturn(Optional.of(createUserEntity()));
    }
}