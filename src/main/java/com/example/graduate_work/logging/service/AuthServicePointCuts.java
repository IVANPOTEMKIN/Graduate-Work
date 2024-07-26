package com.example.graduate_work.logging.service;

import org.aspectj.lang.annotation.Pointcut;

public class AuthServicePointCuts {

    @Pointcut("execution(* com.example.graduate_work.service.impl.AuthServiceImpl.login(..))")
    public void login() {
    }

    @Pointcut("execution(* com.example.graduate_work.service.impl.AuthServiceImpl.register(..))")
    public void register() {
    }
}