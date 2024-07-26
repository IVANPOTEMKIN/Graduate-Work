package com.example.graduate_work.logging.service;

import org.aspectj.lang.annotation.Pointcut;

public class ImageServicePointCuts {

    @Pointcut("execution(* com.example.graduate_work.service.impl.ImageServiceImpl.saveImage(..))")
    public void saveImage() {
    }

    @Pointcut("execution(* com.example.graduate_work.service.impl.ImageServiceImpl.downloadImage(..))")
    public void downloadImage() {
    }

    @Pointcut("execution(* com.example.graduate_work.service.impl.ImageServiceImpl.deleteImage(..))")
    public void deleteImage() {
    }
}