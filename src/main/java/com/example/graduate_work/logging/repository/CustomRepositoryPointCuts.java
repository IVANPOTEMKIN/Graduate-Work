package com.example.graduate_work.logging.repository;

import org.aspectj.lang.annotation.Pointcut;

public class CustomRepositoryPointCuts {

    @Pointcut("execution(* com.example.graduate_work.repository.AdRepository.findAdEntitiesByAuthor(..))")
    public void findAdEntitiesByAuthor() {
    }

    @Pointcut("execution(* com.example.graduate_work.repository.CommentRepository.findCommentEntitiesByAd(..))")
    public void findCommentEntitiesByAd() {
    }

    @Pointcut("execution(* com.example.graduate_work.repository.UserRepository.findUserEntityByUsername(..))")
    public void findUserEntityByUsername() {
    }
}