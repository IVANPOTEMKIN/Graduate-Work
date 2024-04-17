package ru.skypro.homework.exception;

public class FilePathNotFoundException extends RuntimeException {
    public FilePathNotFoundException() {
        super("ФАЙЛ ЗАГРУЗКИ НЕ НАЙДЕН!");
    }
}