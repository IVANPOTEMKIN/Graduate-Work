package ru.skypro.homework.exception;

public class UserAlreadyAddedException extends RuntimeException {
    public UserAlreadyAddedException() {
        super("ПОЛЬЗОВАТЕЛЬ С ДАННЫМ ЛОГИНОМ УЖЕ ДОБАВЛЕН!");
    }
}