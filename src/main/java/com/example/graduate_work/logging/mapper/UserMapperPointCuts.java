package com.example.graduate_work.logging.mapper;

import org.aspectj.lang.annotation.Pointcut;

public class UserMapperPointCuts {

    @Pointcut("execution(* com.example.graduate_work.mapper.impl.UserMapperImpl.toUserDTO(..))")
    public void toUserDTO() {
    }

    @Pointcut("execution(* com.example.graduate_work.mapper.impl.UserMapperImpl.toUpdateUserDTO(..))")
    public void toUpdateUserDTO() {
    }

    @Pointcut("execution(* com.example.graduate_work.mapper.impl.UserMapperImpl.toUserEntity(..))")
    public void toUserEntity() {
    }
}