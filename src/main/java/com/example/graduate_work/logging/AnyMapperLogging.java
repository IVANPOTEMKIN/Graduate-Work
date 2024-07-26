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
public class AnyMapperLogging {

    @Before("com.example.graduate_work.logging.mapper.AdMapperPointCuts.toAdDTO()" +
            "|| com.example.graduate_work.logging.mapper.AdMapperPointCuts.toExtendedAdDTO()" +
            "|| com.example.graduate_work.logging.mapper.AdMapperPointCuts.toAdEntity()" +

            "|| com.example.graduate_work.logging.mapper.CommentMapperPointCuts.toCommentDTO()" +
            "|| com.example.graduate_work.logging.mapper.CommentMapperPointCuts.toCommentEntity()" +

            "|| com.example.graduate_work.logging.mapper.ImageMapperPointCuts.toImageEntity()" +

            "|| com.example.graduate_work.logging.mapper.UserMapperPointCuts.toUserDTO()" +
            "|| com.example.graduate_work.logging.mapper.UserMapperPointCuts.toUpdateUserDTO()" +
            "|| com.example.graduate_work.logging.mapper.UserMapperPointCuts.toUserEntity()"
    )
    public void before(JoinPoint point) {
        String methodName = point.getSignature().getName();
        String methodClass = point.getSignature().getDeclaringType().getSimpleName();
        log.info("Начало работы метода {} маппера {}", methodName, methodClass);
    }

    @AfterReturning("com.example.graduate_work.logging.mapper.AdMapperPointCuts.toAdDTO()" +
            "|| com.example.graduate_work.logging.mapper.AdMapperPointCuts.toExtendedAdDTO()" +
            "|| com.example.graduate_work.logging.mapper.AdMapperPointCuts.toAdEntity()" +

            "|| com.example.graduate_work.logging.mapper.CommentMapperPointCuts.toCommentDTO()" +
            "|| com.example.graduate_work.logging.mapper.CommentMapperPointCuts.toCommentEntity()" +

            "|| com.example.graduate_work.logging.mapper.ImageMapperPointCuts.toImageEntity()" +

            "|| com.example.graduate_work.logging.mapper.UserMapperPointCuts.toUserDTO()" +
            "|| com.example.graduate_work.logging.mapper.UserMapperPointCuts.toUpdateUserDTO()" +
            "|| com.example.graduate_work.logging.mapper.UserMapperPointCuts.toUserEntity()"
    )
    public void after_successful(JoinPoint point) {
        String methodName = point.getSignature().getName();
        String methodClass = point.getSignature().getDeclaringType().getSimpleName();
        log.info("Конец работы метода {} маппера {}", methodName, methodClass);
    }

    @AfterThrowing(value = "com.example.graduate_work.logging.mapper.ImageMapperPointCuts.toImageEntity()",
            throwing = "exception"
    )
    public void after_failed(JoinPoint point, Throwable exception) {
        String methodName = point.getSignature().getName();
        String methodClass = point.getSignature().getDeclaringType().getSimpleName();
        String message = exception.getMessage();
        log.error("Ошибка в работе метода {} маппера {}\n{}", methodName, methodClass, message);
        exception.printStackTrace();
    }
}