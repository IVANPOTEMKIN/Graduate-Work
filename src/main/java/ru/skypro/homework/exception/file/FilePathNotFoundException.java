package ru.skypro.homework.exception.file;

public class FilePathNotFoundException extends RuntimeException {
    public FilePathNotFoundException() {
        super("ФАЙЛ ЗАГРУЗКИ НЕ НАЙДЕН!");
    }
}