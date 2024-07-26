package com.example.graduate_work.exception.password;

public class ReusePasswordException extends RuntimeException {

    public ReusePasswordException() {
        super("ВВЕДЕННЫЙ ПАРОЛЬ УЖЕ ИСПОЛЬЗУЕТСЯ!");
    }
}