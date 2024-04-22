package ru.skypro.homework.dto.comment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class CreateOrUpdateCommentDTO {

    @Schema(description = "Текст комментария")
    @Size(min = 8, max = 64,
            message = "ТЕКСТ КОММЕНТАРИЯ ДОЛЖЕН БЫТЬ В ДИАПАЗОНЕ ОТ 8 ДО 64 СИМВОЛОВ!")
    private String text;
}