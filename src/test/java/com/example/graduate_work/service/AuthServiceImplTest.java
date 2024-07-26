package com.example.graduate_work.service;

import com.example.graduate_work.dto.auth.RegisterDTO;
import com.example.graduate_work.entity.UserEntity;
import com.example.graduate_work.exception.password.WrongPasswordException;
import com.example.graduate_work.exception.user.UserAlreadyAddedException;
import com.example.graduate_work.exception.user.UserNotFoundException;
import com.example.graduate_work.mapper.UserMapper;
import com.example.graduate_work.repository.UserRepository;
import com.example.graduate_work.service.impl.AuthServiceImpl;
import com.example.graduate_work.service.impl.UserDetailsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static com.example.graduate_work.utils.Examples.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

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