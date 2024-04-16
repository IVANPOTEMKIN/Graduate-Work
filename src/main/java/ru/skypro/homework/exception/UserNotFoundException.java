package ru.skypro.homework.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpStatusCodeException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(NOT_FOUND)
public class UserNotFoundException extends HttpStatusCodeException {

    public UserNotFoundException() {
        super(NOT_FOUND, "Пользователь с данным логином не найден!");
    }
}