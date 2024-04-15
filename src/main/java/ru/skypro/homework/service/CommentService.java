package ru.skypro.homework.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.comment.CommentDTO;
import ru.skypro.homework.dto.comment.CommentsDTO;
import ru.skypro.homework.dto.comment.CreateOrUpdateCommentDTO;

public interface CommentService {

    ResponseEntity<CommentsDTO> getAllCommentsOfAd(int id);

    ResponseEntity<CommentDTO> addCommentToAd(int id, CreateOrUpdateCommentDTO dto,
                                              Authentication auth);

    ResponseEntity<?> deleteComment(int idAd, int idComment);

    ResponseEntity<CommentDTO> updateComment(int idAd, int idComment,
                                             CreateOrUpdateCommentDTO dto);
}