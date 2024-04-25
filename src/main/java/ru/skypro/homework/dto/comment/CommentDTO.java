package ru.skypro.homework.dto.comment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * <code> <b> author </b> </code> <i> ID автора комментария </i> <br>
 * <code> <b> authorImage </b> </code> <i> Ссылка на аватар автора комментария </i> <br>
 * <code> <b> authorFirstName </b> </code> <i> Имя создателя комментария </i> <br>
 * <code> <b> createdAt </b> </code> <i> Дата и время создания комментария </i> <br>
 * <code> <b> pk </b> </code> <i> ID комментария </i> <br>
 * <code> <b> text </b> </code> <i> Текст комментария </i> <br>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {

    @Schema(description = "ID автора комментария")
    private Integer author;

    @Schema(description = "Ссылка на аватар автора комментария")
    private String authorImage;

    @Schema(description = "Имя создателя комментария")
    private String authorFirstName;

    @Schema(description = "Дата и время создания комментария")
    private LocalDateTime createdAt;

    @Schema(description = "ID комментария")
    private Integer pk;

    @Schema(description = "Текст комментария")
    private String text;
}