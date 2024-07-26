package com.example.graduate_work.logging.service;

import org.aspectj.lang.annotation.Pointcut;

public class AdServicePointCuts {

    @Pointcut("execution(* com.example.graduate_work.service.impl.AdServiceImpl.getAllAds(..))")
    public void getAllAds() {
    }

    @Pointcut("execution(* com.example.graduate_work.service.impl.AdServiceImpl.addAd(..))")
    public void addAd() {
    }

    @Pointcut("execution(* com.example.graduate_work.service.impl.AdServiceImpl.getInfoAboutAd(..))")
    public void getInfoAboutAd() {
    }

    @Pointcut("execution(* com.example.graduate_work.service.impl.AdServiceImpl.deleteAd(..))")
    public void deleteAd() {
    }

    @Pointcut("execution(* com.example.graduate_work.service.impl.AdServiceImpl.updateInfoAboutAd(..))")
    public void updateInfoAboutAd() {
    }

    @Pointcut("execution(* com.example.graduate_work.service.impl.AdServiceImpl.getAllAdsOfUser(..))")
    public void getAllAdsOfUser() {
    }

    @Pointcut("execution(* com.example.graduate_work.service.impl.AdServiceImpl.updateImageOfAd(..))")
    public void updateImageOfAd() {
    }
}