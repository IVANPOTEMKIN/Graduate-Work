package ru.skypro.homework.logging.service;

import org.aspectj.lang.annotation.Pointcut;

public class UserServicePointCuts {

    @Pointcut("execution(* ru.skypro.homework.service.impl.UserServiceImpl.updatePassword(..))")
    public void updatePassword() {
    }

    @Pointcut("execution(* ru.skypro.homework.service.impl.UserServiceImpl.getInfoAboutUser(..))")
    public void getInfoAboutUser() {
    }

    @Pointcut("execution(* ru.skypro.homework.service.impl.UserServiceImpl.updateInfoAboutUser(..))")
    public void updateInfoAboutUser() {
    }

    @Pointcut("execution(* ru.skypro.homework.service.impl.UserServiceImpl.updateAvatarOfUser(..))")
    public void updateAvatarOfUser() {
    }
}