package ru.skypro.homework.exception.file;

public class FailedSaveFileException extends RuntimeException {
    public FailedSaveFileException() {
        super("ОШИБКА СОХРАНЕНИЯ ФАЙЛА В ДИРЕКТОРИЮ!");
    }
}