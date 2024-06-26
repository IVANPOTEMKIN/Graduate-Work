package ru.skypro.homework.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.skypro.homework.dto.auth.RegisterDTO;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.password.WrongPasswordException;
import ru.skypro.homework.exception.user.UserAlreadyAddedException;
import ru.skypro.homework.exception.user.UserNotFoundException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.impl.AuthServiceImpl;
import ru.skypro.homework.service.impl.UserDetailsServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static ru.skypro.homework.utils.Examples.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @Mock
    private PasswordEncoder encoder;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;
    @Mock
    private UserDetailsServiceImpl detailsService;
    @InjectMocks
    private AuthServiceImpl authService;

    @BeforeEach
    void setUp() {
        authService = new AuthServiceImpl(encoder, userRepository, userMapper, detailsService);
    }

    // login

    @Test
    void login_successful() {
        loadUser();

        when(encoder.matches(anyString(), anyString()))
                .thenReturn(true);

        assertTrue(authService.login(createLoginDTO()));

        verify(detailsService, times(1))
                .loadUserByUsername(anyString());
        verify(encoder, times(1))
                .matches(anyString(), anyString());
    }

    @Test
    void login_UserNotFoundException() {
        when(detailsService.loadUserByUsername(anyString()))
                .thenThrow(UserNotFoundException.class);

        assertThrows(UserNotFoundException.class,
                () -> authService.login(createLoginDTO()));

        verify(detailsService, times(1))
                .loadUserByUsername(anyString());
        verify(encoder, times(0))
                .matches(anyString(), anyString());
    }

    @Test
    void login_WrongPasswordException() {
        loadUser();

        when(encoder.matches(anyString(), anyString()))
                .thenReturn(false);

        assertThrows(WrongPasswordException.class,
                () -> authService.login(createLoginDTO()));

        verify(detailsService, times(1))
                .loadUserByUsername(anyString());
        verify(encoder, times(1))
                .matches(anyString(), anyString());
    }

    // register

    @Test
    void register_successful() {
        when(userMapper.toUserEntity(any(RegisterDTO.class)))
                .thenReturn(createUserEntity());

        assertTrue(authService.register(createRegisterDTO()));

        verify(userMapper, times(1))
                .toUserEntity(any(RegisterDTO.class));
        verify(userRepository, times(1))
                .findUserEntityByUsername(anyString());
        verify(encoder, times(1))
                .encode(anyString());
        verify(userRepository, times(1))
                .save(any(UserEntity.class));
    }

    @Test
    void register_UserAlreadyAddedException() {
        when(userMapper.toUserEntity(any(RegisterDTO.class)))
                .thenReturn(createUserEntity());
        when(userRepository.findUserEntityByUsername(anyString()))
                .thenReturn(Optional.of(createUserEntity()));

        assertThrows(UserAlreadyAddedException.class,
                () -> authService.register(createRegisterDTO()));

        verify(userMapper, times(1))
                .toUserEntity(any(RegisterDTO.class));
        verify(userRepository, times(1))
                .findUserEntityByUsername(anyString());
        verify(encoder, times(0))
                .encode(anyString());
        verify(userRepository, times(0))
                .save(any(UserEntity.class));
    }

    private void loadUser() {
        when(detailsService.loadUserByUsername(anyString()))
                .thenReturn(createUserDetails());
    }
}