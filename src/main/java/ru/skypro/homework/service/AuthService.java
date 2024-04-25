package ru.skypro.homework.service;

import ru.skypro.homework.dto.auth.LoginDTO;
import ru.skypro.homework.dto.auth.RegisterDTO;

import javax.validation.Valid;

public interface AuthService {

    /**
     * Авторизация пользователя
     *
     * @param dto {@link LoginDTO}
     * @return {@link Boolean} <i> Результат выполнения метода </i>
     */
    boolean login(@Valid LoginDTO dto);

    /**
     * Регистрация пользователя
     *
     * @param dto {@link RegisterDTO}
     * @return {@link Boolean} <i> Результат выполнения метода </i>
     */
    boolean register(@Valid RegisterDTO dto);
}