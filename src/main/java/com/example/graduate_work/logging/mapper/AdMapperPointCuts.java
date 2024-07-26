package com.example.graduate_work.logging.mapper;

import org.aspectj.lang.annotation.Pointcut;

public class AdMapperPointCuts {

    @Pointcut("execution(* com.example.graduate_work.mapper.impl.AdMapperImpl.toAdDTO(..))")
    public void toAdDTO() {
    }

    @Pointcut("execution(* com.example.graduate_work.mapper.impl.AdMapperImpl.toExtendedAdDTO(..))")
    public void toExtendedAdDTO() {
    }

    @Pointcut("execution(* com.example.graduate_work.mapper.impl.AdMapperImpl.toAdEntity(..))")
    public void toAdEntity() {
    }
}