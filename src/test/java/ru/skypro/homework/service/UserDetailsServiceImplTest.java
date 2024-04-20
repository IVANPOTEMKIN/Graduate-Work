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
    private UserRepository repository;

    @InjectMocks
    private UserDetailsServiceImpl service;

    @BeforeEach
    public void setUp() {
        service = new UserDetailsServiceImpl(repository);
    }

    @Test
    void loadUserByUsername_successful() {
        when(repository.findUserEntityByUsername(anyString()))
                .thenReturn(Optional.of(createUserEntity()));

        UserDetails expected = createUserDetails();
        UserDetails actual = service.loadUserByUsername(anyString());

        assertNotNull(actual);
        assertEquals(expected, actual);

        verify(repository, times(1))
                .findUserEntityByUsername(anyString());
    }

    @Test
    void loadUserByUsername_exception() {
        when(repository.findUserEntityByUsername(anyString()))
                .thenThrow(UserNotFoundException.class);

        assertThrows(UserNotFoundException.class,
                () -> service.loadUserByUsername(anyString()));

        verify(repository, times(1))
                .findUserEntityByUsername(anyString());
    }
}