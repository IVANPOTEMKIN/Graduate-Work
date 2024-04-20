package ru.skypro.homework.logging.service;

import org.aspectj.lang.annotation.Pointcut;

public class UserDetailsServicePointCuts {

    @Pointcut("execution(* ru.skypro.homework.service.impl.UserDetailsServiceImpl.loadUserByUsername(..))")
    public void loadUserByUsername() {
    }
}