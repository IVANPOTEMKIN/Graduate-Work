package ru.skypro.homework.utils.mapper.comment;

import ru.skypro.homework.dto.comment.CommentDTO;
import ru.skypro.homework.dto.comment.CreateOrUpdateCommentDTO;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;

import java.time.LocalDateTime;

import static ru.skypro.homework.utils.mapper.Constants.*;

public class MappingToCommentEntity {

    public static CommentDTO createCommentDTO() {
        CommentDTO dto = new CommentDTO();

        dto.setAuthor(ID);
        dto.setAuthorImage(FILE_PATH);
        dto.setAuthorFirstName(FIRST_NAME);
        dto.setCreatedAt(LocalDateTime.MIN);
        dto.setPk(ID);
        dto.setText(TEXT);

        return dto;
    }

    public static CreateOrUpdateCommentDTO createCreateOrUpdateCommentDTO() {
        CreateOrUpdateCommentDTO dto = new CreateOrUpdateCommentDTO();

        dto.setText(TEXT);

        return dto;
    }

    public static CommentEntity createCommentEntityFromCommentDTO() {
        CommentDTO dto = createCommentDTO();
        CommentEntity entity = new CommentEntity();

        entity.setAuthor(new UserEntity());
        entity.getAuthor().setAvatar(new ImageEntity());

        entity.getAuthor().setId(dto.getAuthor());
        entity.getAuthor().getAvatar().setFilePath(dto.getAuthorImage());
        entity.getAuthor().setFirstName(dto.getAuthorFirstName());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setId(dto.getPk());
        entity.setText(dto.getText());

        return entity;
    }

    public static CommentEntity createCommentEntityFromCreateOrUpdateCommentDTO() {
        CreateOrUpdateCommentDTO dto = createCreateOrUpdateCommentDTO();
        CommentEntity entity = new CommentEntity();

        entity.setText(dto.getText());

        return entity;
    }
}