package ru.skypro.homework.exception.password;

public class ReusePasswordException extends RuntimeException {
    public ReusePasswordException() {
        super("ВВЕДЕННЫЙ ПАРОЛЬ УЖЕ ИСПОЛЬЗУЕТСЯ!");
    }
}