package com.example.graduate_work.exception;

public class ImageNotFoundException extends RuntimeException {

    public ImageNotFoundException() {
        super("ИЗОБРАЖЕНИЕ НЕ НАЙДЕНО!");
    }
}