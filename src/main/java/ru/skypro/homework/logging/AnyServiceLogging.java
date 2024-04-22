package ru.skypro.homework.logging;

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

    @Before("ru.skypro.homework.logging.service.AdServicePointCuts.getAllAds()" +
            "|| ru.skypro.homework.logging.service.AdServicePointCuts.addAd()" +
            "|| ru.skypro.homework.logging.service.AdServicePointCuts.getInfoAboutAd()" +
            "|| ru.skypro.homework.logging.service.AdServicePointCuts.deleteAd()" +
            "|| ru.skypro.homework.logging.service.AdServicePointCuts.updateInfoAboutAd()" +
            "|| ru.skypro.homework.logging.service.AdServicePointCuts.getAllAdsOfUser()" +
            "|| ru.skypro.homework.logging.service.AdServicePointCuts.updateImageOfAd()" +

            "|| ru.skypro.homework.logging.service.AuthServicePointCuts.login()" +
            "|| ru.skypro.homework.logging.service.AuthServicePointCuts.register()" +

            "|| ru.skypro.homework.logging.service.CommentServicePointCuts.getAllCommentsOfAd()" +
            "|| ru.skypro.homework.logging.service.CommentServicePointCuts.addCommentToAd()" +
            "|| ru.skypro.homework.logging.service.CommentServicePointCuts.deleteComment()" +
            "|| ru.skypro.homework.logging.service.CommentServicePointCuts.updateComment()" +

            "|| ru.skypro.homework.logging.service.ImageServicePointCuts.saveImage()" +
            "|| ru.skypro.homework.logging.service.ImageServicePointCuts.downloadImage()" +
            "|| ru.skypro.homework.logging.service.ImageServicePointCuts.deleteImage()" +

            "|| ru.skypro.homework.logging.service.UserDetailsServicePointCuts.loadUserByUsername()" +

            "|| ru.skypro.homework.logging.service.UserServicePointCuts.updatePassword()" +
            "|| ru.skypro.homework.logging.service.UserServicePointCuts.getInfoAboutUser()" +
            "|| ru.skypro.homework.logging.service.UserServicePointCuts.updateInfoAboutUser()" +
            "|| ru.skypro.homework.logging.service.UserServicePointCuts.updateAvatarOfUser()"
    )
    public void before(JoinPoint point) {
        String methodName = point.getSignature().getName();
        String methodClass = point.getSignature().getDeclaringType().getSimpleName();
        log.info("Начало работы метода {} сервиса {}", methodName, methodClass);
    }

    @AfterReturning("ru.skypro.homework.logging.service.AdServicePointCuts.getAllAds()" +
            "|| ru.skypro.homework.logging.service.AdServicePointCuts.addAd()" +
            "|| ru.skypro.homework.logging.service.AdServicePointCuts.getInfoAboutAd()" +
            "|| ru.skypro.homework.logging.service.AdServicePointCuts.deleteAd()" +
            "|| ru.skypro.homework.logging.service.AdServicePointCuts.updateInfoAboutAd()" +
            "|| ru.skypro.homework.logging.service.AdServicePointCuts.getAllAdsOfUser()" +
            "|| ru.skypro.homework.logging.service.AdServicePointCuts.updateImageOfAd()" +

            "|| ru.skypro.homework.logging.service.AuthServicePointCuts.login()" +
            "|| ru.skypro.homework.logging.service.AuthServicePointCuts.register()" +

            "|| ru.skypro.homework.logging.service.CommentServicePointCuts.getAllCommentsOfAd()" +
            "|| ru.skypro.homework.logging.service.CommentServicePointCuts.addCommentToAd()" +
            "|| ru.skypro.homework.logging.service.CommentServicePointCuts.deleteComment()" +
            "|| ru.skypro.homework.logging.service.CommentServicePointCuts.updateComment()" +

            "|| ru.skypro.homework.logging.service.ImageServicePointCuts.saveImage()" +
            "|| ru.skypro.homework.logging.service.ImageServicePointCuts.downloadImage()" +
            "|| ru.skypro.homework.logging.service.ImageServicePointCuts.deleteImage()" +

            "|| ru.skypro.homework.logging.service.UserDetailsServicePointCuts.loadUserByUsername()" +

            "|| ru.skypro.homework.logging.service.UserServicePointCuts.updatePassword()" +
            "|| ru.skypro.homework.logging.service.UserServicePointCuts.getInfoAboutUser()" +
            "|| ru.skypro.homework.logging.service.UserServicePointCuts.updateInfoAboutUser()" +
            "|| ru.skypro.homework.logging.service.UserServicePointCuts.updateAvatarOfUser()"
    )
    public void after_successful(JoinPoint point) {
        String methodName = point.getSignature().getName();
        String methodClass = point.getSignature().getDeclaringType().getSimpleName();
        log.info("Конец работы метода {} сервиса {}", methodName, methodClass);
    }

    @AfterThrowing(value = "ru.skypro.homework.logging.service.AdServicePointCuts.getAllAds()" +
            "|| ru.skypro.homework.logging.service.AdServicePointCuts.addAd()" +
            "|| ru.skypro.homework.logging.service.AdServicePointCuts.getInfoAboutAd()" +
            "|| ru.skypro.homework.logging.service.AdServicePointCuts.deleteAd()" +
            "|| ru.skypro.homework.logging.service.AdServicePointCuts.updateInfoAboutAd()" +
            "|| ru.skypro.homework.logging.service.AdServicePointCuts.getAllAdsOfUser()" +
            "|| ru.skypro.homework.logging.service.AdServicePointCuts.updateImageOfAd()" +

            "|| ru.skypro.homework.logging.service.AuthServicePointCuts.login()" +
            "|| ru.skypro.homework.logging.service.AuthServicePointCuts.register()" +

            "|| ru.skypro.homework.logging.service.CommentServicePointCuts.getAllCommentsOfAd()" +
            "|| ru.skypro.homework.logging.service.CommentServicePointCuts.addCommentToAd()" +
            "|| ru.skypro.homework.logging.service.CommentServicePointCuts.deleteComment()" +
            "|| ru.skypro.homework.logging.service.CommentServicePointCuts.updateComment()" +

            "|| ru.skypro.homework.logging.service.ImageServicePointCuts.saveImage()" +
            "|| ru.skypro.homework.logging.service.ImageServicePointCuts.downloadImage()" +
            "|| ru.skypro.homework.logging.service.ImageServicePointCuts.deleteImage()" +

            "|| ru.skypro.homework.logging.service.UserDetailsServicePointCuts.loadUserByUsername()" +

            "|| ru.skypro.homework.logging.service.UserServicePointCuts.updatePassword()" +
            "|| ru.skypro.homework.logging.service.UserServicePointCuts.getInfoAboutUser()" +
            "|| ru.skypro.homework.logging.service.UserServicePointCuts.updateInfoAboutUser()" +
            "|| ru.skypro.homework.logging.service.UserServicePointCuts.updateAvatarOfUser()",
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