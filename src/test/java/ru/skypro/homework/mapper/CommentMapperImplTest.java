package ru.skypro.homework.mapper;

import org.junit.jupiter.api.Test;
import ru.skypro.homework.dto.comment.CommentDTO;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.mapper.impl.CommentMapperImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static ru.skypro.homework.utils.Examples.createCommentEntity;
import static ru.skypro.homework.utils.Examples.createCreateOrUpdateCommentDTO;
import static ru.skypro.homework.utils.mapper.CommentMapperUtils.getCommentDTO_From_CommentEntity;
import static ru.skypro.homework.utils.mapper.CommentMapperUtils.getCommentEntity_From_CreateOrUpdateCommentDTO;

class CommentMapperImplTest {

    private final CommentMapper mapper = new CommentMapperImpl();

    @Test
    void toCommentDTO() {
        CommentDTO expected = getCommentDTO_From_CommentEntity();
        CommentDTO actual = mapper.toCommentDTO(createCommentEntity());

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    void toCommentEntity() {
        CommentEntity expected = getCommentEntity_From_CreateOrUpdateCommentDTO();
        CommentEntity actual = mapper.toCommentEntity(createCreateOrUpdateCommentDTO());

        assertNotNull(actual);
        assertEquals(expected, actual);
    }
}