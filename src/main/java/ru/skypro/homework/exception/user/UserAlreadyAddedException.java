package ru.skypro.homework.exception.user;

public class UserAlreadyAddedException extends RuntimeException {
    public UserAlreadyAddedException() {
        super("ПОЛЬЗОВАТЕЛЬ С ДАННЫМ ЛОГИНОМ УЖЕ ДОБАВЛЕН!");
    }
}