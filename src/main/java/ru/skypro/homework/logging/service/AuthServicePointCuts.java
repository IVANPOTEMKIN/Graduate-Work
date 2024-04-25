package ru.skypro.homework.logging.service;

import org.aspectj.lang.annotation.Pointcut;

public class AuthServicePointCuts {

    @Pointcut("execution(* ru.skypro.homework.service.impl.AuthServiceImpl.login(..))")
    public void login() {
    }

    @Pointcut("execution(* ru.skypro.homework.service.impl.AuthServiceImpl.register(..))")
    public void register() {
    }
}