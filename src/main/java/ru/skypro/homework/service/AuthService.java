package ru.skypro.homework.service;

import ru.skypro.homework.dto.auth.LoginDTO;
import ru.skypro.homework.dto.auth.RegisterDTO;

import javax.validation.Valid;

public interface AuthService {

    void login(@Valid LoginDTO dto);

    void register(@Valid RegisterDTO dto);
}