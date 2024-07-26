package com.example.graduate_work.utils.mapper;

import com.example.graduate_work.dto.comment.CommentDTO;
import com.example.graduate_work.dto.comment.CreateOrUpdateCommentDTO;
import com.example.graduate_work.entity.CommentEntity;

import static com.example.graduate_work.utils.Examples.createCommentEntity;
import static com.example.graduate_work.utils.Examples.createCreateOrUpdateCommentDTO;
import static com.example.graduate_work.utils.URL.USER;
import static java.time.LocalDateTime.MIN;

public class CommentMapperUtils {

    public static CommentDTO getCommentDTO_From_CommentEntity() {
        CommentEntity entity = createCommentEntity();
        CommentDTO dto = new CommentDTO();

        dto.setAuthor(entity.getAuthor().getId());
        dto.setAuthorImage(USER.getUrl() + entity.getAuthor().getId());
        dto.setAuthorFirstName(entity.getAuthor().getFirstName());
        dto.setCreatedAt(MIN);
        dto.setPk(entity.getId());
        dto.setText(entity.getText());

        return dto;
    }

    public static CommentEntity getCommentEntity_From_CreateOrUpdateCommentDTO() {
        CreateOrUpdateCommentDTO dto = createCreateOrUpdateCommentDTO();
        CommentEntity entity = new CommentEntity();

        entity.setText(dto.getText());

        return entity;
    }
}