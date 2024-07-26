package com.example.graduate_work.logging.mapper;

import org.aspectj.lang.annotation.Pointcut;

public class CommentMapperPointCuts {

    @Pointcut("execution(* com.example.graduate_work.mapper.impl.CommentMapperImpl.toCommentDTO(..))")
    public void toCommentDTO() {
    }

    @Pointcut("execution(* com.example.graduate_work.mapper.impl.CommentMapperImpl.toCommentEntity(..))")
    public void toCommentEntity() {
    }
}