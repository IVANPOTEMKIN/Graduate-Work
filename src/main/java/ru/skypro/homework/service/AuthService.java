package ru.skypro.homework.service;

import org.springframework.http.ResponseEntity;
import ru.skypro.homework.dto.auth.LoginDTO;
import ru.skypro.homework.dto.auth.RegisterDTO;

import javax.validation.Valid;

public interface AuthService {

    boolean login(@Valid LoginDTO dto);

    ResponseEntity<?> register(@Valid RegisterDTO dto);
}