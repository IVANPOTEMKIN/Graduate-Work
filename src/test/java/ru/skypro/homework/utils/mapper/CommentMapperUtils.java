package ru.skypro.homework.utils.mapper;

import ru.skypro.homework.dto.comment.CommentDTO;
import ru.skypro.homework.dto.comment.CreateOrUpdateCommentDTO;
import ru.skypro.homework.entity.CommentEntity;

import static java.time.LocalDateTime.MIN;
import static ru.skypro.homework.constants.URL.USER;
import static ru.skypro.homework.utils.Constants.ID;
import static ru.skypro.homework.utils.Constants.TEXT;
import static ru.skypro.homework.utils.mapper.AdMapperUtils.createAdEntity;
import static ru.skypro.homework.utils.mapper.UserMapperUtils.createUserEntity;

public class CommentMapperUtils {

    public static CommentEntity createEntity() {
        CommentEntity entity = new CommentEntity();

        entity.setId(ID);
        entity.setCreatedAt(MIN);
        entity.setText(TEXT);
        entity.setAd(createAdEntity());
        entity.setAuthor(createUserEntity());

        return entity;
    }

    public static CreateOrUpdateCommentDTO createDTO() {
        CreateOrUpdateCommentDTO dto = new CreateOrUpdateCommentDTO();

        dto.setText(TEXT);

        return dto;
    }

    public static CommentDTO toCommentDTO_From_CommentEntity() {
        CommentEntity entity = createEntity();
        CommentDTO dto = new CommentDTO();

        dto.setAuthor(entity.getAuthor().getId());
        dto.setAuthorImage(USER.getUrl() + entity.getAuthor().getId());
        dto.setAuthorFirstName(entity.getAuthor().getFirstName());
        dto.setCreatedAt(MIN);
        dto.setPk(entity.getId());
        dto.setText(entity.getText());

        return dto;
    }

    public static CommentEntity toCommentEntity_From_CreateOrUpdateCommentDTO() {
        CreateOrUpdateCommentDTO dto = createDTO();
        CommentEntity entity = new CommentEntity();

        entity.setText(dto.getText());

        return entity;
    }
}