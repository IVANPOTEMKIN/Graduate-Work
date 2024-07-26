package com.example.graduate_work.controller;

import com.example.graduate_work.exception.AdNotFoundException;
import com.example.graduate_work.exception.CommentNotFoundException;
import com.example.graduate_work.exception.ImageNotFoundException;
import com.example.graduate_work.exception.file.FailedRecordFileException;
import com.example.graduate_work.exception.file.FailedSaveFileException;
import com.example.graduate_work.exception.file.FilePathNotFoundException;
import com.example.graduate_work.exception.password.ReusePasswordException;
import com.example.graduate_work.exception.password.WrongPasswordException;
import com.example.graduate_work.exception.user.UserAlreadyAddedException;
import com.example.graduate_work.exception.user.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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