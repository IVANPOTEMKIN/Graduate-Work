package ru.skypro.homework.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("ПОЛЬЗОВАТЕЛЬ С ДАННЫМ ЛОГИНОМ НЕ НАЙДЕН!");
    }
}