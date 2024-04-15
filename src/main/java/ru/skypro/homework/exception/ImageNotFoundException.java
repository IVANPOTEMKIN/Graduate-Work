package ru.skypro.homework.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpStatusCodeException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(NOT_FOUND)
public class ImageNotFoundException extends HttpStatusCodeException {

    public ImageNotFoundException() {
        super(NOT_FOUND, "Изображение не найдено!");
    }
}