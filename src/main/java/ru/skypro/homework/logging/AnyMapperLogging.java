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
public class AnyMapperLogging {

    @Before("ru.skypro.homework.logging.mapper.AdMapperPointCuts.toAdDTO()" +
            "|| ru.skypro.homework.logging.mapper.AdMapperPointCuts.toExtendedAdDTO()" +
            "|| ru.skypro.homework.logging.mapper.AdMapperPointCuts.toAdEntity()" +

            "|| ru.skypro.homework.logging.mapper.CommentMapperPointCuts.toCommentDTO()" +
            "|| ru.skypro.homework.logging.mapper.CommentMapperPointCuts.toCommentEntity()" +

            "|| ru.skypro.homework.logging.mapper.ImageMapperPointCuts.toImageEntity()" +

            "|| ru.skypro.homework.logging.mapper.UserMapperPointCuts.toUserDTO()" +
            "|| ru.skypro.homework.logging.mapper.UserMapperPointCuts.toUpdateUserDTO()" +
            "|| ru.skypro.homework.logging.mapper.UserMapperPointCuts.toUserEntity()"
    )
    public void before(JoinPoint point) {
        String methodName = point.getSignature().getName();
        String methodClass = point.getSignature().getDeclaringType().getSimpleName();
        log.info("Начало работы метода {} маппера {}", methodName, methodClass);
    }

    @AfterReturning("ru.skypro.homework.logging.mapper.AdMapperPointCuts.toAdDTO()" +
            "|| ru.skypro.homework.logging.mapper.AdMapperPointCuts.toExtendedAdDTO()" +
            "|| ru.skypro.homework.logging.mapper.AdMapperPointCuts.toAdEntity()" +

            "|| ru.skypro.homework.logging.mapper.CommentMapperPointCuts.toCommentDTO()" +
            "|| ru.skypro.homework.logging.mapper.CommentMapperPointCuts.toCommentEntity()" +

            "|| ru.skypro.homework.logging.mapper.ImageMapperPointCuts.toImageEntity()" +

            "|| ru.skypro.homework.logging.mapper.UserMapperPointCuts.toUserDTO()" +
            "|| ru.skypro.homework.logging.mapper.UserMapperPointCuts.toUpdateUserDTO()" +
            "|| ru.skypro.homework.logging.mapper.UserMapperPointCuts.toUserEntity()"
    )
    public void after_successful(JoinPoint point) {
        String methodName = point.getSignature().getName();
        String methodClass = point.getSignature().getDeclaringType().getSimpleName();
        log.info("Конец работы метода {} маппера {}", methodName, methodClass);
    }

    @AfterThrowing(value = "ru.skypro.homework.logging.mapper.ImageMapperPointCuts.toImageEntity()",
            throwing = "exception"
    )
    public void after_failed(JoinPoint point, Throwable exception) {
        String methodName = point.getSignature().getName();
        String methodClass = point.getSignature().getDeclaringType().getSimpleName();
        String message = exception.getMessage();
        log.error("Ошибка в работе метода {} маппера {}\n{}", methodName, methodClass, message);
    }
}