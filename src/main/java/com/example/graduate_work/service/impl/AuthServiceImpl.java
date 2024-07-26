package com.example.graduate_work.service.impl;

import com.example.graduate_work.dto.auth.LoginDTO;
import com.example.graduate_work.dto.auth.RegisterDTO;
import com.example.graduate_work.entity.UserEntity;
import com.example.graduate_work.exception.password.WrongPasswordException;
import com.example.graduate_work.exception.user.UserAlreadyAddedException;
import com.example.graduate_work.mapper.UserMapper;
import com.example.graduate_work.repository.UserRepository;
import com.example.graduate_work.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;

@Service
@Validated
@RequiredArgsConstructor
@Transactional(isolation = SERIALIZABLE)
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserDetailsServiceImpl detailsService;

    @Override
    public boolean login(LoginDTO dto) {
        UserDetails details = detailsService.loadUserByUsername(dto.getUsername());

        if (!encoder.matches(dto.getPassword(), details.getPassword())) {
            throw new WrongPasswordException();
        }
        return true;
    }

    @Override
    public boolean register(RegisterDTO dto) {
        UserEntity user = userMapper.toUserEntity(dto);

        if (userRepository.findUserEntityByUsername(user.getUsername()).isPresent()) {
            throw new UserAlreadyAddedException();
        }

        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }
}