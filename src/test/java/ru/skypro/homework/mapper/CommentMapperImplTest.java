package ru.skypro.homework.mapper;

import org.junit.jupiter.api.Test;
import ru.skypro.homework.dto.comment.CommentDTO;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.mapper.impl.CommentMapperImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static ru.skypro.homework.utils.mapper.CommentMapperUtils.*;

class CommentMapperImplTest {

    private final CommentMapper mapper = new CommentMapperImpl();

    @Test
    void toCommentDTO() {
        CommentDTO expected = toCommentDTO_From_CommentEntity();
        CommentDTO actual = mapper.toCommentDTO(createEntity());

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    void toCommentEntity() {
        CommentEntity expected = toCommentEntity_From_CreateOrUpdateCommentDTO();
        CommentEntity actual = mapper.toCommentEntity(createDTO());

        assertNotNull(actual);
        assertEquals(expected, actual);
    }
}