package com.example.graduate_work.mapper;

import com.example.graduate_work.dto.comment.CommentDTO;
import com.example.graduate_work.dto.comment.CreateOrUpdateCommentDTO;
import com.example.graduate_work.entity.CommentEntity;

public interface CommentMapper {

    CommentDTO toCommentDTO(CommentEntity entity);

    CommentEntity toCommentEntity(CreateOrUpdateCommentDTO dto);
}