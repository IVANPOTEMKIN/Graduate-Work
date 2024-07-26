package com.example.graduate_work.exception.file;

public class FailedRecordFileException extends RuntimeException {

    public FailedRecordFileException() {
        super("ОШИБКА ЗАПИСИ ФАЙЛА!");
    }
}