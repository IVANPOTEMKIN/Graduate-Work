package com.example.graduate_work.logging.service;

import org.aspectj.lang.annotation.Pointcut;

public class UserServicePointCuts {

    @Pointcut("execution(* com.example.graduate_work.service.impl.UserServiceImpl.updatePassword(..))")
    public void updatePassword() {
    }

    @Pointcut("execution(* com.example.graduate_work.service.impl.UserServiceImpl.getInfoAboutUser(..))")
    public void getInfoAboutUser() {
    }

    @Pointcut("execution(* com.example.graduate_work.service.impl.UserServiceImpl.updateInfoAboutUser(..))")
    public void updateInfoAboutUser() {
    }

    @Pointcut("execution(* com.example.graduate_work.service.impl.UserServiceImpl.updateAvatarOfUser(..))")
    public void updateAvatarOfUser() {
    }
}