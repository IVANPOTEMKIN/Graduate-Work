package ru.skypro.homework.dto.comment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CommentsDTO {

    @Schema(description = "общее количество комментариев")
    private Integer count;

    private List<CommentDTO> results;
}