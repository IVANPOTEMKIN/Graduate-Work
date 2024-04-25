package ru.skypro.homework.logging.mapper;

import org.aspectj.lang.annotation.Pointcut;

public class CommentMapperPointCuts {

    @Pointcut("execution(* ru.skypro.homework.mapper.impl.CommentMapperImpl.toCommentDTO(..))")
    public void toCommentDTO() {
    }

    @Pointcut("execution(* ru.skypro.homework.mapper.impl.CommentMapperImpl.toCommentEntity(..))")
    public void toCommentEntity() {
    }
}