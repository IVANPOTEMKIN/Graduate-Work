package ru.skypro.homework.logging.mapper;

import org.aspectj.lang.annotation.Pointcut;

public class ImageMapperPointCuts {

    @Pointcut("execution(* ru.skypro.homework.mapper.impl.ImageMapperImpl.toImageEntity(..))")
    public void toImageEntity() {
    }
}