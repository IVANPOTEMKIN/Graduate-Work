package com.example.graduate_work.logging.mapper;

import org.aspectj.lang.annotation.Pointcut;

public class ImageMapperPointCuts {

    @Pointcut("execution(* com.example.graduate_work.mapper.impl.ImageMapperImpl.toImageEntity(..))")
    public void toImageEntity() {
    }
}