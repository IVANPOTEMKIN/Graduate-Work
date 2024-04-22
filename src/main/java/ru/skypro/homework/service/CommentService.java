package ru.skypro.homework.service;

import ru.skypro.homework.dto.comment.CommentDTO;
import ru.skypro.homework.dto.comment.CommentsDTO;
import ru.skypro.homework.dto.comment.CreateOrUpdateCommentDTO;

import javax.validation.Valid;

public interface CommentService {

    CommentsDTO getAllCommentsOfAd(int id);

    CommentDTO addCommentToAd(int id, @Valid CreateOrUpdateCommentDTO dto);

    boolean deleteComment(int idAd, int idComment);

    CommentDTO updateComment(int idAd, int idComment,
                             @Valid CreateOrUpdateCommentDTO dto);
}