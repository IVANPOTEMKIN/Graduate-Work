package com.example.graduate_work.logging.service;

import org.aspectj.lang.annotation.Pointcut;

public class CommentServicePointCuts {

    @Pointcut("execution(* com.example.graduate_work.service.impl.CommentServiceImpl.getAllCommentsOfAd(..))")
    public void getAllCommentsOfAd() {
    }

    @Pointcut("execution(* com.example.graduate_work.service.impl.CommentServiceImpl.addCommentToAd(..))")
    public void addCommentToAd() {
    }

    @Pointcut("execution(* com.example.graduate_work.service.impl.CommentServiceImpl.deleteComment(..))")
    public void deleteComment() {
    }

    @Pointcut("execution(* com.example.graduate_work.service.impl.CommentServiceImpl.updateComment(..))")
    public void updateComment() {
    }
}