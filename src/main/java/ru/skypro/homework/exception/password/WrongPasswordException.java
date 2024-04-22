package ru.skypro.homework.exception.password;

public class WrongPasswordException extends RuntimeException {
    public WrongPasswordException() {
        super("ВВЕДЕН НЕВЕРНЫЙ ПАРОЛЬ!");
    }
}