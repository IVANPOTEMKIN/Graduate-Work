package com.example.graduate_work.service.impl;

import com.example.graduate_work.entity.UserEntity;
import com.example.graduate_work.exception.user.UserNotFoundException;
import com.example.graduate_work.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserEntity entity = userRepository.findUserEntityByUsername(username)
                .orElseThrow(UserNotFoundException::new);

        return User.builder()
                .username(entity.getUsername())
                .password(entity.getPassword())
                .roles(entity.getRole().name())
                .build();
    }
}