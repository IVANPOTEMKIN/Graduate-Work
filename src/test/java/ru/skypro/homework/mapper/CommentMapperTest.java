package ru.skypro.homework.mapper;

import org.junit.jupiter.api.Test;
import ru.skypro.homework.dto.comment.CommentDTO;
import ru.skypro.homework.dto.comment.CreateOrUpdateCommentDTO;
import ru.skypro.homework.entity.CommentEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static ru.skypro.homework.mapper.CommentMapper.INSTANCE;
import static ru.skypro.homework.utils.mapper.comment.MappingFromCommentEntity.*;
import static ru.skypro.homework.utils.mapper.comment.MappingToCommentEntity.*;

public class CommentMapperTest {

    @Test
    void to_CommentDTO_From_CommentEntity() {
        CommentEntity entity = createCommentEntity();

        CommentDTO expected = createCommentDTOFromCommentEntity();
        CommentDTO actual = INSTANCE.toCommentDTO(entity);

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    void to_CreateOrUpdateCommentDTO_From_CommentEntity() {
        CommentEntity entity = createCommentEntity();

        CreateOrUpdateCommentDTO expected = createCreateOrUpdateCommentDTOFromCommentEntity();
        CreateOrUpdateCommentDTO actual = INSTANCE.toCreateOrUpdateCommentDTO(entity);

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    void to_CommentEntity_From_CommentDTO() {
        CommentDTO dto = createCommentDTO();

        CommentEntity expected = createCommentEntityFromCommentDTO();
        CommentEntity actual = INSTANCE.toCommentEntity(dto);

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    void to_CommentEntity_From_CreateOrUpdateCommentDTO() {
        CreateOrUpdateCommentDTO dto = createCreateOrUpdateCommentDTO();

        CommentEntity expected = createCommentEntityFromCreateOrUpdateCommentDTO();
        CommentEntity actual = INSTANCE.toCommentEntity(dto);

        assertNotNull(actual);
        assertEquals(expected, actual);
    }
}