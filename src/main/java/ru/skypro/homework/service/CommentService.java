package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.comment.CommentDTO;
import ru.skypro.homework.dto.comment.CommentsDTO;
import ru.skypro.homework.dto.comment.CreateOrUpdateCommentDTO;
import ru.skypro.homework.entity.CommentEntity;

public interface CommentService {

    CommentsDTO getAllCommentsOfAd(int id);

    CommentDTO addCommentToAd(int id, CreateOrUpdateCommentDTO dto,
                              Authentication auth);

    void deleteComment(int idAd, int idComment);

    CommentDTO updateComment(int idAd, int idComment,
                             CreateOrUpdateCommentDTO dto);

    CommentEntity getById(int id);
}