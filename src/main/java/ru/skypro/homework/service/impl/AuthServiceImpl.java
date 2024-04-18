package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.skypro.homework.dto.auth.LoginDTO;
import ru.skypro.homework.dto.auth.RegisterDTO;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.password.WrongPasswordException;
import ru.skypro.homework.exception.user.UserAlreadyAddedException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;

@Service
@Validated
@RequiredArgsConstructor
@Transactional(isolation = SERIALIZABLE)
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder encoder;
    private final UserRepository repository;
    private final UserMapper mapper;
    private final UserDetailsService service;

    @Override
    public ResponseEntity<?> login(LoginDTO dto) {
        UserDetails details = service.loadUserByUsername(dto.getUsername());

        if (!encoder.matches(dto.getPassword(), details.getPassword())) {
            throw new WrongPasswordException();
        }
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> register(RegisterDTO dto) {
        UserEntity user = mapper.toUserEntity(dto);

        if (repository.findUserEntityByUsername(user.getUsername()).isPresent()) {
            throw new UserAlreadyAddedException();
        }

        user.setPassword(encoder.encode(user.getPassword()));
        repository.save(user);
        return ResponseEntity.status(CREATED).build();
    }
}