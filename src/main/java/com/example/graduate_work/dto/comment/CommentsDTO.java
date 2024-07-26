package com.example.graduate_work.dto.comment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentsDTO {

    @Schema(description = "Общее количество комментариев")
    private Integer count;

    private List<CommentDTO> results;
}