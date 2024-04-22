package ru.skypro.homework.logging.service;

import org.aspectj.lang.annotation.Pointcut;

public class AdServicePointCuts {

    @Pointcut("execution(* ru.skypro.homework.service.impl.AdServiceImpl.getAllAds(..))")
    public void getAllAds() {
    }

    @Pointcut("execution(* ru.skypro.homework.service.impl.AdServiceImpl.addAd(..))")
    public void addAd() {
    }

    @Pointcut("execution(* ru.skypro.homework.service.impl.AdServiceImpl.getInfoAboutAd(..))")
    public void getInfoAboutAd() {
    }

    @Pointcut("execution(* ru.skypro.homework.service.impl.AdServiceImpl.deleteAd(..))")
    public void deleteAd() {
    }

    @Pointcut("execution(* ru.skypro.homework.service.impl.AdServiceImpl.updateInfoAboutAd(..))")
    public void updateInfoAboutAd() {
    }

    @Pointcut("execution(* ru.skypro.homework.service.impl.AdServiceImpl.getAllAdsOfUser(..))")
    public void getAllAdsOfUser() {
    }

    @Pointcut("execution(* ru.skypro.homework.service.impl.AdServiceImpl.updateImageOfAd(..))")
    public void updateImageOfAd() {
    }
}