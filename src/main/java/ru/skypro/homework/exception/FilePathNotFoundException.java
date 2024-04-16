package ru.skypro.homework.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpStatusCodeException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(NOT_FOUND)
public class FilePathNotFoundException extends HttpStatusCodeException {

    public FilePathNotFoundException() {
        super(NOT_FOUND, "Файл по данному пути не найден!");
    }
}