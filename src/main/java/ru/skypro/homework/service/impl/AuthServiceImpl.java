package ru.skypro.homework.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.auth.RegisterDTO;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.UserAlreadyAddedException;
import ru.skypro.homework.exception.WrongPasswordException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder encoder;
    private final UserRepository repository;
    private final UserMapper mapper;
    private final UserDetailsService service;

    public AuthServiceImpl(PasswordEncoder encoder,
                           UserRepository repository,
                           UserMapper mapper) {

        this.encoder = encoder;
        this.repository = repository;
        this.mapper = mapper;
        this.service = new UserDetailsServiceImpl(repository);
    }

    @Override
    public boolean login(String username, String password) {
        UserDetails userDetails = service.loadUserByUsername(username);

        if (!encoder.matches(password, userDetails.getPassword())) {
            throw new WrongPasswordException();
        }
        return true;
    }

    @Override
    public boolean register(RegisterDTO dto) {
        UserEntity user = mapper.toUserEntity(dto);

        if (repository.findUserEntityByUsername(user.getUsername()).isPresent()) {
            throw new UserAlreadyAddedException();
        }

        user.setPassword(encoder.encode(user.getPassword()));
        repository.save(user);
        return true;
    }
}