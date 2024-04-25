package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.comment.CommentDTO;
import ru.skypro.homework.dto.comment.CreateOrUpdateCommentDTO;
import ru.skypro.homework.entity.CommentEntity;

public interface CommentMapper {

    /**
     * {@link CommentEntity} -> {@link CommentDTO}
     */
    CommentDTO toCommentDTO(CommentEntity entity);

    /**
     * {@link CreateOrUpdateCommentDTO} -> {@link CommentEntity}
     */
    CommentEntity toCommentEntity(CreateOrUpdateCommentDTO dto);
}