package ru.skypro.homework.dto.comment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <code> <b> count </b> </code> <i> Общее количество комментариев </i> <br>
 * <code> <b> results </b> </code> <code> List<{@link CommentDTO}> </code> <br>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentsDTO {

    @Schema(description = "Общее количество комментариев")
    private Integer count;

    private List<CommentDTO> results;
}