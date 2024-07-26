package com.example.graduate_work.service;

import com.example.graduate_work.exception.user.UserNotFoundException;
import com.example.graduate_work.repository.UserRepository;
import com.example.graduate_work.service.impl.UserDetailsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static com.example.graduate_work.utils.Examples.createUserDetails;
import static com.example.graduate_work.utils.Examples.createUserEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserDetailsServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDetailsServiceImpl detailsService;

    @BeforeEach
    public void setUp() {
        detailsService = new UserDetailsServiceImpl(userRepository);
    }

    @Test
    void loadUserByUsername_successful() {
        when(userRepository.findUserEntityByUsername(anyString()))
                .thenReturn(Optional.of(createUserEntity()));

        UserDetails expected = createUserDetails();
        UserDetails actual = detailsService.loadUserByUsername(anyString());

        assertNotNull(actual);
        assertEquals(expected, actual);

        verify(userRepository, times(1))
                .findUserEntityByUsername(anyString());
    }

    @Test
    void loadUserByUsername_UserNotFoundException() {
        when(userRepository.findUserEntityByUsername(anyString()))
                .thenThrow(UserNotFoundException.class);

        assertThrows(UserNotFoundException.class,
                () -> detailsService.loadUserByUsername(anyString()));

        verify(userRepository, times(1))
                .findUserEntityByUsername(anyString());
    }
}