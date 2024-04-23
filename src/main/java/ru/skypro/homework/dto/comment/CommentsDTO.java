package ru.skypro.homework.dto.comment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * <code> <b> count </b> </code> <i> Общее количество комментариев </i> <br>
 * <code> <b> results </b> </code> <i> Список CommentDTO </i> <br>
 */
@Data
@AllArgsConstructor
public class CommentsDTO {

    @Schema(description = "Общее количество комментариев")
    private Integer count;

    private List<CommentDTO> results;
}