package ru.skypro.homework.logging.service;

import org.aspectj.lang.annotation.Pointcut;

public class CommentServicePointCuts {

    @Pointcut("execution(* ru.skypro.homework.service.impl.CommentServiceImpl.getAllCommentsOfAd(..))")
    public void getAllCommentsOfAd() {
    }

    @Pointcut("execution(* ru.skypro.homework.service.impl.CommentServiceImpl.addCommentToAd(..))")
    public void addCommentToAd() {
    }

    @Pointcut("execution(* ru.skypro.homework.service.impl.CommentServiceImpl.deleteComment(..))")
    public void deleteComment() {
    }

    @Pointcut("execution(* ru.skypro.homework.service.impl.CommentServiceImpl.updateComment(..))")
    public void updateComment() {
    }
}