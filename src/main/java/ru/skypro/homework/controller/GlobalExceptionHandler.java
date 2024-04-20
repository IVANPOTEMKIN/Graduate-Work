package ru.skypro.homework.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.exception.CommentNotFoundException;
import ru.skypro.homework.exception.ImageNotFoundException;
import ru.skypro.homework.exception.file.FailedRecordFileException;
import ru.skypro.homework.exception.file.FailedSaveFileException;
import ru.skypro.homework.exception.file.FilePathNotFoundException;
import ru.skypro.homework.exception.password.ReusePasswordException;
import ru.skypro.homework.exception.password.WrongPasswordException;
import ru.skypro.homework.exception.user.UserAlreadyAddedException;
import ru.skypro.homework.exception.user.UserNotFoundException;

import javax.validation.ConstraintViolationException;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {
            WrongPasswordException.class,
            ReusePasswordException.class,
            UserAlreadyAddedException.class})
    public ResponseEntity<String> handle_BadRequest_Exception(Throwable e) {
        return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<String> handle_Validation_Exception(Throwable e) {
        return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    public ResponseEntity<String> handle_Access_Exception() {
        return ResponseEntity.status(FORBIDDEN).body("ОШИБКА ДОСТУПА!");
    }

    @ExceptionHandler(value = {
            ImageNotFoundException.class,
            FilePathNotFoundException.class,
            UserNotFoundException.class,
            AdNotFoundException.class,
            CommentNotFoundException.class})
    public ResponseEntity<String> handle_NotFound_Exception(Throwable e) {
        return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(value = {
            FailedSaveFileException.class,
            FailedRecordFileException.class})
    public ResponseEntity<String> handle_ExpectationFailed_Exception(Throwable e) {
        return ResponseEntity.status(EXPECTATION_FAILED).body(e.getMessage());
    }
}