package com.example.graduate_work.service;

import com.example.graduate_work.dto.auth.LoginDTO;
import com.example.graduate_work.dto.auth.RegisterDTO;

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