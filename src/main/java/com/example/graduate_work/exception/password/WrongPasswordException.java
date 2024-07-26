package com.example.graduate_work.exception.password;

public class WrongPasswordException extends RuntimeException {

    public WrongPasswordException() {
        super("ВВЕДЕН НЕВЕРНЫЙ ПАРОЛЬ!");
    }
}