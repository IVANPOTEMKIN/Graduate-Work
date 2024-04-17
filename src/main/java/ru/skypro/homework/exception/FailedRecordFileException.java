package ru.skypro.homework.exception;

public class FailedRecordFileException extends RuntimeException {
    public FailedRecordFileException() {
        super("ОШИБКА ЗАПИСИ ФАЙЛА!");
    }
}