package ru.skypro.homework.utils.mapper.comment;

import ru.skypro.homework.dto.comment.CommentDTO;
import ru.skypro.homework.dto.comment.CreateOrUpdateCommentDTO;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;

import java.time.LocalDateTime;

import static ru.skypro.homework.utils.mapper.Constants.*;

public class MappingFromCommentEntity {

    public static CommentEntity createCommentEntity() {
        CommentEntity entity = new CommentEntity();

        entity.setAuthor(new UserEntity());
        entity.getAuthor().setAvatar(new ImageEntity());

        entity.setId(ID);
        entity.setCreatedAt(LocalDateTime.MIN);
        entity.setText(TEXT);
        entity.getAuthor().getAvatar().setFilePath(FILE_PATH);
        entity.getAuthor().setFirstName(FIRST_NAME);

        return entity;
    }

    public static CommentDTO createCommentDTOFromCommentEntity() {
        CommentEntity entity = createCommentEntity();
        CommentDTO dto = new CommentDTO();

        dto.setAuthor(entity.getAuthor().getId());
        dto.setAuthorImage(entity.getAuthor().getAvatar().getFilePath());
        dto.setAuthorFirstName(entity.getAuthor().getFirstName());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setPk(entity.getId());
        dto.setText(entity.getText());

        return dto;
    }

    public static CreateOrUpdateCommentDTO createCreateOrUpdateCommentDTOFromCommentEntity() {
        CommentEntity entity = createCommentEntity();
        CreateOrUpdateCommentDTO dto = new CreateOrUpdateCommentDTO();

        dto.setText(entity.getText());

        return dto;
    }
}