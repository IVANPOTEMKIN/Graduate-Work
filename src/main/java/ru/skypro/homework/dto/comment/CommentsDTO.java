package ru.skypro.homework.dto.comment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class CommentsDTO {

    @Schema(description = "общее количество комментариев")
    private Integer count;

    private List<CommentDTO> results;
}