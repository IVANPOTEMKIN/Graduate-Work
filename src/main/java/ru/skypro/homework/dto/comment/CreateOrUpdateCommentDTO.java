package ru.skypro.homework.dto.comment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

/**
 * <code> <b> text </b> </code> <i> Текст комментария </i> <br>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrUpdateCommentDTO {

    @Schema(description = "Текст комментария")
    @Size(min = 8, max = 64,
            message = "ТЕКСТ КОММЕНТАРИЯ ДОЛЖЕН БЫТЬ В ДИАПАЗОНЕ ОТ 8 ДО 64 СИМВОЛОВ!")
    private String text;
}