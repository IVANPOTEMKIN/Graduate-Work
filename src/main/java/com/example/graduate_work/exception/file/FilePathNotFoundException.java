package com.example.graduate_work.exception.file;

public class FilePathNotFoundException extends RuntimeException {

    public FilePathNotFoundException() {
        super("ФАЙЛ ЗАГРУЗКИ НЕ НАЙДЕН!");
    }
}