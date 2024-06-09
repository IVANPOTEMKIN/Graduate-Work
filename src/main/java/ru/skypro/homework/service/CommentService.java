package ru.skypro.homework.service;

import ru.skypro.homework.dto.rq.comment.Comment;
import ru.skypro.homework.dto.rq.comment.CreateOrUpdateComment;
import ru.skypro.homework.dto.rs.comment.Comments;
import ru.skypro.homework.entity.CommentEntity;

public interface CommentService {
    Comments getCommentsByAd(Integer id);

    Comment postComment(Integer id, CreateOrUpdateComment createOrUpdateComment);
}
