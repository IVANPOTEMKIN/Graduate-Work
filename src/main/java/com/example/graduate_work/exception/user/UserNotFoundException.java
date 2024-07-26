package com.example.graduate_work.exception.user;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("ПОЛЬЗОВАТЕЛЬ С ДАННЫМ ЛОГИНОМ НЕ НАЙДЕН!");
    }
}