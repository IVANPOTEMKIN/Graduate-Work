package ru.skypro.homework.logging;

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

    @Before("ru.skypro.homework.logging.repository.CRUDRepositoryPointCuts.save()" +
            "||ru.skypro.homework.logging.repository.CRUDRepositoryPointCuts.delete()" +
            "||ru.skypro.homework.logging.repository.CRUDRepositoryPointCuts.findById()" +
            "||ru.skypro.homework.logging.repository.CRUDRepositoryPointCuts.findAll()" +

            "||ru.skypro.homework.logging.repository.CustomRepositoryPointCuts.findAdEntitiesByAuthor()" +
            "||ru.skypro.homework.logging.repository.CustomRepositoryPointCuts.findCommentEntitiesByAd()" +
            "||ru.skypro.homework.logging.repository.CustomRepositoryPointCuts.findUserEntityByUsername()"
    )
    public void before(JoinPoint point) {
        String methodName = point.getSignature().getName();
        String methodClass = point.getSignature().getDeclaringType().getSimpleName();
        log.info("Начало работы метода {} репозитория {}", methodName, methodClass);
    }

    @AfterReturning("ru.skypro.homework.logging.repository.CRUDRepositoryPointCuts.save()" +
            "||ru.skypro.homework.logging.repository.CRUDRepositoryPointCuts.delete()" +
            "||ru.skypro.homework.logging.repository.CRUDRepositoryPointCuts.findById()" +
            "||ru.skypro.homework.logging.repository.CRUDRepositoryPointCuts.findAll()" +

            "||ru.skypro.homework.logging.repository.CustomRepositoryPointCuts.findAdEntitiesByAuthor()" +
            "||ru.skypro.homework.logging.repository.CustomRepositoryPointCuts.findCommentEntitiesByAd()" +
            "||ru.skypro.homework.logging.repository.CustomRepositoryPointCuts.findUserEntityByUsername()"
    )
    public void after_successful(JoinPoint point) {
        String methodName = point.getSignature().getName();
        String methodClass = point.getSignature().getDeclaringType().getSimpleName();
        log.info("Конец работы метода {} репозитория {}", methodName, methodClass);
    }
}