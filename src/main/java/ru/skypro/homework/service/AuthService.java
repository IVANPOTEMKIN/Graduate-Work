package ru.skypro.homework.service;

import ru.skypro.homework.dto.auth.LoginDTO;
import ru.skypro.homework.dto.auth.RegisterDTO;

import javax.validation.Valid;

public interface AuthService {

    boolean login(@Valid LoginDTO dto);

    boolean register(@Valid RegisterDTO dto);
}