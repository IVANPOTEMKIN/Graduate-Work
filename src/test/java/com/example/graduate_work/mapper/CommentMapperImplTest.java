package com.example.graduate_work.mapper;

import com.example.graduate_work.dto.comment.CommentDTO;
import com.example.graduate_work.entity.CommentEntity;
import com.example.graduate_work.mapper.impl.CommentMapperImpl;
import org.junit.jupiter.api.Test;

import static com.example.graduate_work.utils.Examples.createCommentEntity;
import static com.example.graduate_work.utils.Examples.createCreateOrUpdateCommentDTO;
import static com.example.graduate_work.utils.mapper.CommentMapperUtils.getCommentDTO_From_CommentEntity;
import static com.example.graduate_work.utils.mapper.CommentMapperUtils.getCommentEntity_From_CreateOrUpdateCommentDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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