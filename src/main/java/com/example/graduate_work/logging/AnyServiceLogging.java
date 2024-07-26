package com.example.graduate_work.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class AnyServiceLogging {

    @Before("com.example.graduate_work.logging.service.AdServicePointCuts.getAllAds()" +
            "|| com.example.graduate_work.logging.service.AdServicePointCuts.addAd()" +
            "|| com.example.graduate_work.logging.service.AdServicePointCuts.getInfoAboutAd()" +
            "|| com.example.graduate_work.logging.service.AdServicePointCuts.deleteAd()" +
            "|| com.example.graduate_work.logging.service.AdServicePointCuts.updateInfoAboutAd()" +
            "|| com.example.graduate_work.logging.service.AdServicePointCuts.getAllAdsOfUser()" +
            "|| com.example.graduate_work.logging.service.AdServicePointCuts.updateImageOfAd()" +

            "|| com.example.graduate_work.logging.service.AuthServicePointCuts.login()" +
            "|| com.example.graduate_work.logging.service.AuthServicePointCuts.register()" +

            "|| com.example.graduate_work.logging.service.CommentServicePointCuts.getAllCommentsOfAd()" +
            "|| com.example.graduate_work.logging.service.CommentServicePointCuts.addCommentToAd()" +
            "|| com.example.graduate_work.logging.service.CommentServicePointCuts.deleteComment()" +
            "|| com.example.graduate_work.logging.service.CommentServicePointCuts.updateComment()" +

            "|| com.example.graduate_work.logging.service.ImageServicePointCuts.saveImage()" +
            "|| com.example.graduate_work.logging.service.ImageServicePointCuts.downloadImage()" +
            "|| com.example.graduate_work.logging.service.ImageServicePointCuts.deleteImage()" +

            "|| com.example.graduate_work.logging.service.UserDetailsServicePointCuts.loadUserByUsername()" +

            "|| com.example.graduate_work.logging.service.UserServicePointCuts.updatePassword()" +
            "|| com.example.graduate_work.logging.service.UserServicePointCuts.getInfoAboutUser()" +
            "|| com.example.graduate_work.logging.service.UserServicePointCuts.updateInfoAboutUser()" +
            "|| com.example.graduate_work.logging.service.UserServicePointCuts.updateAvatarOfUser()"
    )
    public void before(JoinPoint point) {
        String methodName = point.getSignature().getName();
        String methodClass = point.getSignature().getDeclaringType().getSimpleName();
        log.info("Начало работы метода {} сервиса {}", methodName, methodClass);
    }

    @AfterReturning("com.example.graduate_work.logging.service.AdServicePointCuts.getAllAds()" +
            "|| com.example.graduate_work.logging.service.AdServicePointCuts.addAd()" +
            "|| com.example.graduate_work.logging.service.AdServicePointCuts.getInfoAboutAd()" +
            "|| com.example.graduate_work.logging.service.AdServicePointCuts.deleteAd()" +
            "|| com.example.graduate_work.logging.service.AdServicePointCuts.updateInfoAboutAd()" +
            "|| com.example.graduate_work.logging.service.AdServicePointCuts.getAllAdsOfUser()" +
            "|| com.example.graduate_work.logging.service.AdServicePointCuts.updateImageOfAd()" +

            "|| com.example.graduate_work.logging.service.AuthServicePointCuts.login()" +
            "|| com.example.graduate_work.logging.service.AuthServicePointCuts.register()" +

            "|| com.example.graduate_work.logging.service.CommentServicePointCuts.getAllCommentsOfAd()" +
            "|| com.example.graduate_work.logging.service.CommentServicePointCuts.addCommentToAd()" +
            "|| com.example.graduate_work.logging.service.CommentServicePointCuts.deleteComment()" +
            "|| com.example.graduate_work.logging.service.CommentServicePointCuts.updateComment()" +

            "|| com.example.graduate_work.logging.service.ImageServicePointCuts.saveImage()" +
            "|| com.example.graduate_work.logging.service.ImageServicePointCuts.downloadImage()" +
            "|| com.example.graduate_work.logging.service.ImageServicePointCuts.deleteImage()" +

            "|| com.example.graduate_work.logging.service.UserDetailsServicePointCuts.loadUserByUsername()" +

            "|| com.example.graduate_work.logging.service.UserServicePointCuts.updatePassword()" +
            "|| com.example.graduate_work.logging.service.UserServicePointCuts.getInfoAboutUser()" +
            "|| com.example.graduate_work.logging.service.UserServicePointCuts.updateInfoAboutUser()" +
            "|| com.example.graduate_work.logging.service.UserServicePointCuts.updateAvatarOfUser()"
    )
    public void after_successful(JoinPoint point) {
        String methodName = point.getSignature().getName();
        String methodClass = point.getSignature().getDeclaringType().getSimpleName();
        log.info("Конец работы метода {} сервиса {}", methodName, methodClass);
    }

    @AfterThrowing(value = "com.example.graduate_work.logging.service.AdServicePointCuts.getAllAds()" +
            "|| com.example.graduate_work.logging.service.AdServicePointCuts.addAd()" +
            "|| com.example.graduate_work.logging.service.AdServicePointCuts.getInfoAboutAd()" +
            "|| com.example.graduate_work.logging.service.AdServicePointCuts.deleteAd()" +
            "|| com.example.graduate_work.logging.service.AdServicePointCuts.updateInfoAboutAd()" +
            "|| com.example.graduate_work.logging.service.AdServicePointCuts.getAllAdsOfUser()" +
            "|| com.example.graduate_work.logging.service.AdServicePointCuts.updateImageOfAd()" +

            "|| com.example.graduate_work.logging.service.AuthServicePointCuts.login()" +
            "|| com.example.graduate_work.logging.service.AuthServicePointCuts.register()" +

            "|| com.example.graduate_work.logging.service.CommentServicePointCuts.getAllCommentsOfAd()" +
            "|| com.example.graduate_work.logging.service.CommentServicePointCuts.addCommentToAd()" +
            "|| com.example.graduate_work.logging.service.CommentServicePointCuts.deleteComment()" +
            "|| com.example.graduate_work.logging.service.CommentServicePointCuts.updateComment()" +

            "|| com.example.graduate_work.logging.service.ImageServicePointCuts.saveImage()" +
            "|| com.example.graduate_work.logging.service.ImageServicePointCuts.downloadImage()" +
            "|| com.example.graduate_work.logging.service.ImageServicePointCuts.deleteImage()" +

            "|| com.example.graduate_work.logging.service.UserDetailsServicePointCuts.loadUserByUsername()" +

            "|| com.example.graduate_work.logging.service.UserServicePointCuts.updatePassword()" +
            "|| com.example.graduate_work.logging.service.UserServicePointCuts.getInfoAboutUser()" +
            "|| com.example.graduate_work.logging.service.UserServicePointCuts.updateInfoAboutUser()" +
            "|| com.example.graduate_work.logging.service.UserServicePointCuts.updateAvatarOfUser()",
            throwing = "exception"
    )
    public void after_failed(JoinPoint point, Throwable exception) {
        String methodName = point.getSignature().getName();
        String methodClass = point.getSignature().getDeclaringType().getSimpleName();
        String message = exception.getMessage();
        log.error("Ошибка в работе метода {} сервиса {}\n{}", methodName, methodClass, message);
        exception.printStackTrace();
    }
}