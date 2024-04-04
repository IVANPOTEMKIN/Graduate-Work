package ru.skypro.homework.dto.comment;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CommentDTO {

    private int author; // id автора комментария
    private String authorImage; // ссылка на аватар автора комментария
    private String authorFirstName; // имя создателя комментария
    private Timestamp createdAt; // дата и время создания комментария в миллисекундах
    private int pk; // id комментария
    private String text; // текст комментария
}