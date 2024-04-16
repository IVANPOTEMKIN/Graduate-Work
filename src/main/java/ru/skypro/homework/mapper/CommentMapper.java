package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.comment.CommentDTO;
import ru.skypro.homework.dto.comment.CreateOrUpdateCommentDTO;
import ru.skypro.homework.entity.CommentEntity;

public interface CommentMapper {

    CommentDTO toCommentDTO(CommentEntity entity);

    CommentEntity toCommentEntity(CreateOrUpdateCommentDTO dto);
}