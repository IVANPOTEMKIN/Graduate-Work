package ru.skypro.homework.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.skypro.homework.exception.*;

import javax.validation.ConstraintDeclarationException;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<String> handleException(FailedSaveFileException e) {
        return ResponseEntity.status(EXPECTATION_FAILED).body(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleException(FailedRecordFileException e) {
        return ResponseEntity.status(EXPECTATION_FAILED).body(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleException(ImageNotFoundException e) {
        return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleException(FilePathNotFoundException e) {
        return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleException(UserAlreadyAddedException e) {
        return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleException(UserNotFoundException e) {
        return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleException(WrongPasswordException e) {
        return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleException(Throwable e) {
        e.initCause(new ConstraintDeclarationException());
        return ResponseEntity.status(BAD_REQUEST).body("ВВЕДЕНЫ НЕККОРЕКТНЫЕ ДАННЫЕ!");
    }
}