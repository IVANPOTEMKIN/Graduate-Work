package com.example.graduate_work.exception;

public class AdNotFoundException extends RuntimeException {

    public AdNotFoundException() {
        super("ОБЪЯВЛЕНИЕ НЕ НАЙДЕНО!");
    }
}