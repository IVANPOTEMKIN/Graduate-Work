package ru.skypro.homework.exception;

public class ImageNotFoundException extends RuntimeException {
    public ImageNotFoundException() {
        super("ИЗОБРАЖЕНИЕ НЕ НАЙДЕНО!");
    }
}