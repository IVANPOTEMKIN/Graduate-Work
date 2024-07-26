package com.example.graduate_work.exception.file;

public class FailedSaveFileException extends RuntimeException {

    public FailedSaveFileException() {
        super("ОШИБКА СОХРАНЕНИЯ ФАЙЛА В ДИРЕКТОРИЮ!");
    }
}