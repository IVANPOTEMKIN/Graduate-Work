package com.example.graduate_work.mapper.impl;

import com.example.graduate_work.dto.comment.CommentDTO;
import com.example.graduate_work.dto.comment.CreateOrUpdateCommentDTO;
import com.example.graduate_work.entity.CommentEntity;
import com.example.graduate_work.mapper.CommentMapper;
import org.springframework.stereotype.Service;

import static com.example.graduate_work.utils.URL.USER;

@Service
public class CommentMapperImpl implements CommentMapper {

    @Override
    public CommentDTO toCommentDTO(CommentEntity entity) {
        CommentDTO dto = new CommentDTO();

        dto.setAuthor(entity.getAuthor().getId());

        if (entity.getAuthor().getAvatar() != null) {
            dto.setAuthorImage(USER.getUrl() + entity.getAuthor().getAvatar().getId());
        }

        dto.setAuthorFirstName(entity.getAuthor().getFirstName());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setPk(entity.getId());
        dto.setText(entity.getText());

        return dto;
    }

    @Override
    public CommentEntity toCommentEntity(CreateOrUpdateCommentDTO dto) {
        CommentEntity entity = new CommentEntity();
        entity.setText(dto.getText());
        return entity;
    }
}