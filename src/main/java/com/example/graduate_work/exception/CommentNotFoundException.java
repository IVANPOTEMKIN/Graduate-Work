package com.example.graduate_work.exception;

public class CommentNotFoundException extends RuntimeException {

    public CommentNotFoundException() {
        super("КОММЕНТАРИЙ НЕ НАЙДЕН!");
    }
}