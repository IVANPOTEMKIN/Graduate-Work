package ru.skypro.homework.logging.repository;

import org.aspectj.lang.annotation.Pointcut;

public class CustomRepositoryPointCuts {

    @Pointcut("execution(* ru.skypro.homework.repository.AdRepository.findAdEntitiesByAuthor(..))")
    public void findAdEntitiesByAuthor() {
    }

    @Pointcut("execution(* ru.skypro.homework.repository.CommentRepository.findCommentEntitiesByAd(..))")
    public void findCommentEntitiesByAd() {
    }

    @Pointcut("execution(* ru.skypro.homework.repository.UserRepository.findUserEntityByUsername(..))")
    public void findUserEntityByUsername() {
    }
}