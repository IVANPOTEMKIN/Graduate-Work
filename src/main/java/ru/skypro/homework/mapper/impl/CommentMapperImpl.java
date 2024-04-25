package ru.skypro.homework.mapper.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.comment.CommentDTO;
import ru.skypro.homework.dto.comment.CreateOrUpdateCommentDTO;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.mapper.CommentMapper;

import static ru.skypro.homework.constants.URL.USER;

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