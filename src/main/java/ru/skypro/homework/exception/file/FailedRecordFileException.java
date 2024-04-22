package ru.skypro.homework.exception.file;

public class FailedRecordFileException extends RuntimeException {
    public FailedRecordFileException() {
        super("ОШИБКА ЗАПИСИ ФАЙЛА!");
    }
}