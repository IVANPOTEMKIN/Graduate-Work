package ru.skypro.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Ad not found")
public class AdNotFoundException extends RuntimeException {
    public AdNotFoundException() {
    }

    public AdNotFoundException(String message) {
        super(message);
    }
}
