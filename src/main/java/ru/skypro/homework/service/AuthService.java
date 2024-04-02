package ru.skypro.homework.service;

import ru.skypro.homework.dto.RegisterDTO;

public interface AuthService {

    boolean login(String username, String password);

    boolean register(RegisterDTO register);
}