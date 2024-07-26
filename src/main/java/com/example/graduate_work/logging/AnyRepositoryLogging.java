package com.example.graduate_work.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class AnyRepositoryLogging {

    @Before("com.example.graduate_work.logging.repository.CRUDRepositoryPointCuts.save()" +
            "||com.example.graduate_work.logging.repository.CRUDRepositoryPointCuts.delete()" +
            "||com.example.graduate_work.logging.repository.CRUDRepositoryPointCuts.findById()" +
            "||com.example.graduate_work.logging.repository.CRUDRepositoryPointCuts.findAll()" +

            "||com.example.graduate_work.logging.repository.CustomRepositoryPointCuts.findAdEntitiesByAuthor()" +
            "||com.example.graduate_work.logging.repository.CustomRepositoryPointCuts.findCommentEntitiesByAd()" +
            "||com.example.graduate_work.logging.repository.CustomRepositoryPointCuts.findUserEntityByUsername()"
    )
    public void before(JoinPoint point) {
        String methodName = point.getSignature().getName();
        String methodClass = point.getSignature().getDeclaringType().getSimpleName();
        log.info("Начало работы метода {} репозитория {}", methodName, methodClass);
    }

    @AfterReturning("com.example.graduate_work.logging.repository.CRUDRepositoryPointCuts.save()" +
            "||com.example.graduate_work.logging.repository.CRUDRepositoryPointCuts.delete()" +
            "||com.example.graduate_work.logging.repository.CRUDRepositoryPointCuts.findById()" +
            "||com.example.graduate_work.logging.repository.CRUDRepositoryPointCuts.findAll()" +

            "||com.example.graduate_work.logging.repository.CustomRepositoryPointCuts.findAdEntitiesByAuthor()" +
            "||com.example.graduate_work.logging.repository.CustomRepositoryPointCuts.findCommentEntitiesByAd()" +
            "||com.example.graduate_work.logging.repository.CustomRepositoryPointCuts.findUserEntityByUsername()"
    )
    public void after_successful(JoinPoint point) {
        String methodName = point.getSignature().getName();
        String methodClass = point.getSignature().getDeclaringType().getSimpleName();
        log.info("Конец работы метода {} репозитория {}", methodName, methodClass);
    }
}