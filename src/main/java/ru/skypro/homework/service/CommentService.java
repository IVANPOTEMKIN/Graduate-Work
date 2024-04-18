package ru.skypro.homework.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.comment.CommentDTO;
import ru.skypro.homework.dto.comment.CommentsDTO;
import ru.skypro.homework.dto.comment.CreateOrUpdateCommentDTO;

import javax.validation.Valid;

public interface CommentService {

    ResponseEntity<CommentsDTO> getAllCommentsOfAd(int id);

    ResponseEntity<CommentDTO> addCommentToAd(int id, @Valid CreateOrUpdateCommentDTO dto,
                                              Authentication auth);

    ResponseEntity<?> deleteComment(int idAd, int idComment);

    ResponseEntity<CommentDTO> updateComment(int idAd, int idComment,
                                             @Valid CreateOrUpdateCommentDTO dto);
}