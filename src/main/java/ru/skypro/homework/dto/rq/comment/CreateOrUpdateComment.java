package ru.skypro.homework.dto.rq.comment;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateOrUpdateComment {
    /**
     * Поля относящиеся к сущности CommentEntity
     */
    private String text;
}
