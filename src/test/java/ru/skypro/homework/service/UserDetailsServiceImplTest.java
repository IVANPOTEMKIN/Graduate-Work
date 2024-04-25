package ru.skypro.homework.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import ru.skypro.homework.exception.user.UserNotFoundException;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.impl.UserDetailsServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static ru.skypro.homework.utils.Examples.createUserDetails;
import static ru.skypro.homework.utils.Examples.createUserEntity;

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