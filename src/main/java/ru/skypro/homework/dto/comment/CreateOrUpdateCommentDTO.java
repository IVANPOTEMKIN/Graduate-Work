package ru.skypro.homework.dto.comment;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class CreateOrUpdateCommentDTO {

    @Size(min = 8, max = 64)
    private String text; // текст комментария
}