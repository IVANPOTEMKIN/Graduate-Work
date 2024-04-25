package ru.skypro.homework.service;

import ru.skypro.homework.dto.comment.CommentDTO;
import ru.skypro.homework.dto.comment.CommentsDTO;
import ru.skypro.homework.dto.comment.CreateOrUpdateCommentDTO;

import javax.validation.Valid;

public interface CommentService {

    /**
     * Получение комментариев объявления
     *
     * @param id <i> ID комментария </i>
     * @return {@link CommentsDTO}
     */
    CommentsDTO getAllCommentsOfAd(int id);

    /**
     * Добавление комментария к объявлению
     *
     * @param id  <i> ID объявления </i>
     * @param dto {@link CreateOrUpdateCommentDTO}
     * @return {@link CommentDTO}
     */
    CommentDTO addCommentToAd(int id, @Valid CreateOrUpdateCommentDTO dto);

    /**
     * Удаление комментария
     *
     * @param idAd      <i> ID объявления </i>
     * @param idComment <i> ID комментария </i>
     * @return {@link Boolean} <i> Результат выполнения метода </i>
     */
    boolean deleteComment(int idAd, int idComment);

    /**
     * Обновление комментария
     *
     * @param idAd      <i> ID объявления </i>
     * @param idComment <i> ID комментария </i>
     * @param dto       {@link CreateOrUpdateCommentDTO}
     * @return {@link CommentDTO}
     */
    CommentDTO updateComment(int idAd, int idComment,
                             @Valid CreateOrUpdateCommentDTO dto);
}