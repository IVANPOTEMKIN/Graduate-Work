package ru.skypro.homework.logging.mapper;

import org.aspectj.lang.annotation.Pointcut;

public class AdMapperPointCuts {

    @Pointcut("execution(* ru.skypro.homework.mapper.impl.AdMapperImpl.toAdDTO(..))")
    public void toAdDTO() {
    }

    @Pointcut("execution(* ru.skypro.homework.mapper.impl.AdMapperImpl.toExtendedAdDTO(..))")
    public void toExtendedAdDTO() {
    }

    @Pointcut("execution(* ru.skypro.homework.mapper.impl.AdMapperImpl.toAdEntity(..))")
    public void toAdEntity() {
    }
}