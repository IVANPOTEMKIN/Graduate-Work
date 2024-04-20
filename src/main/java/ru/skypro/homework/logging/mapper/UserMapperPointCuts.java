package ru.skypro.homework.logging.mapper;

import org.aspectj.lang.annotation.Pointcut;

public class UserMapperPointCuts {

    @Pointcut("execution(* ru.skypro.homework.mapper.impl.UserMapperImpl.toUserDTO(..))")
    public void toUserDTO() {
    }

    @Pointcut("execution(* ru.skypro.homework.mapper.impl.UserMapperImpl.toUpdateUserDTO(..))")
    public void toUpdateUserDTO() {
    }

    @Pointcut("execution(* ru.skypro.homework.mapper.impl.UserMapperImpl.toUserEntity(..))")
    public void toUserEntity() {
    }
}