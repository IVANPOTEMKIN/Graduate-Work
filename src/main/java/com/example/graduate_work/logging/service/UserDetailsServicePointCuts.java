package com.example.graduate_work.logging.service;

import org.aspectj.lang.annotation.Pointcut;

public class UserDetailsServicePointCuts {

    @Pointcut("execution(* com.example.graduate_work.service.impl.UserDetailsServiceImpl.loadUserByUsername(..))")
    public void loadUserByUsername() {
    }
}