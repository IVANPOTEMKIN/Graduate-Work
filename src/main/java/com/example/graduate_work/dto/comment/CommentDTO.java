package com.example.graduate_work.dto.comment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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