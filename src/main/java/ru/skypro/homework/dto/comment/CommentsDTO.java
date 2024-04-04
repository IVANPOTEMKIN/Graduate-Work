package ru.skypro.homework.dto.comment;

import lombok.Data;

import java.util.List;

@Data
public class CommentsDTO {

    private int count; // общее количество комментариев
    private List<CommentDTO> results;
}