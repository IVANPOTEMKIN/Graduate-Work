package ru.skypro.homework.utils.mapper;

import ru.skypro.homework.dto.comment.CommentDTO;
import ru.skypro.homework.dto.comment.CreateOrUpdateCommentDTO;
import ru.skypro.homework.entity.CommentEntity;

import static java.time.LocalDateTime.MIN;
import static ru.skypro.homework.constants.URL.USER;
import static ru.skypro.homework.utils.Examples.createCommentEntity;
import static ru.skypro.homework.utils.Examples.createCreateOrUpdateCommentDTO;

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