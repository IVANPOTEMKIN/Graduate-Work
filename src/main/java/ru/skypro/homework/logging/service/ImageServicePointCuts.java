package ru.skypro.homework.logging.service;

import org.aspectj.lang.annotation.Pointcut;

public class ImageServicePointCuts {

    @Pointcut("execution(* ru.skypro.homework.service.impl.ImageServiceImpl.saveImage(..))")
    public void saveImage() {
    }

    @Pointcut("execution(* ru.skypro.homework.service.impl.ImageServiceImpl.downloadImage(..))")
    public void downloadImage() {
    }

    @Pointcut("execution(* ru.skypro.homework.service.impl.ImageServiceImpl.deleteImage(..))")
    public void deleteImage() {
    }
}