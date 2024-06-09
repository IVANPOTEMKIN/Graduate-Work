package ru.skypro.homework.dto.rs.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.skypro.homework.dto.rq.comment.Comment;

import java.util.List;

@Data
@AllArgsConstructor
public class Comments {
    private Integer count;
    private List<Comment> results;
}
