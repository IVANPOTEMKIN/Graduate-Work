package ru.skypro.homework.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpStatusCodeException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(BAD_REQUEST)
public class UserAlreadyAddedException extends HttpStatusCodeException {

    public UserAlreadyAddedException() {
        super(BAD_REQUEST, "Пользователь с данным логином уже добавлен!");
    }
}