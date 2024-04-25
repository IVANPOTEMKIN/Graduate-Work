package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.skypro.homework.dto.comment.CommentDTO;
import ru.skypro.homework.dto.comment.CommentsDTO;
import ru.skypro.homework.dto.comment.CreateOrUpdateCommentDTO;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.CommentNotFoundException;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

import static java.time.LocalDateTime.now;
import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;

@Service
@Validated
@RequiredArgsConstructor
@Transactional(isolation = SERIALIZABLE)
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final AdService adService;
    private final UserService userService;
    private final CommentMapper commentMapper;

    @Override
    public CommentsDTO getAllCommentsOfAd(int id) {
        AdEntity ad = adService.getById(id);

        List<CommentDTO> list = commentRepository.findCommentEntitiesByAd(ad)
                .stream()
                .map(commentMapper::toCommentDTO)
                .collect(Collectors.toList());

        return new CommentsDTO(list.size(), list);
    }

    @Override
    public CommentDTO addCommentToAd(int id, CreateOrUpdateCommentDTO dto) {
        UserEntity user = userService.getUser();
        AdEntity ad = adService.getById(id);
        CommentEntity comment = commentMapper.toCommentEntity(dto);

        comment.setCreatedAt(now());
        comment.setAd(ad);
        comment.setAuthor(user);

        commentRepository.save(comment);
        return commentMapper.toCommentDTO(comment);
    }

    @Override
    @PreAuthorize(value = "hasRole('ADMIN')" +
            "or @adServiceImpl.isAuthor(authentication.getName, #idAd)" +
            "or @commentServiceImpl.isAuthor(authentication.getName, #idComment)")
    public boolean deleteComment(int idAd, int idComment) {
        commentRepository.delete(getById(idComment, idAd));
        return true;
    }

    @Override
    @PreAuthorize(value = "@commentServiceImpl.isAuthor(authentication.getName, #idComment)")
    public CommentDTO updateComment(int idAd, int idComment,
                                    CreateOrUpdateCommentDTO dto) {

        CommentEntity comment = getById(idComment, idAd);
        comment.setText(dto.getText());
        comment.setCreatedAt(now());
        commentRepository.save(comment);
        return commentMapper.toCommentDTO(comment);
    }

    /**
     * Проверка соответствия автора комментария с текущим пользователем
     *
     * @param username <code> Authentication.getName </code>
     * @param id       <i> ID комментария </i>
     * @return {@link Boolean} <i> Результат выполнения метода </i>
     */
    public boolean isAuthor(String username, int id) {
        CommentEntity comment = commentRepository.findById(id)
                .orElseThrow(CommentNotFoundException::new);
        return comment.getAuthor().getUsername().equals(username);
    }

    /**
     * Получение комментария по ID объявления и комментария
     *
     * @param idComment <i> ID комментария </i>
     * @param idAd      <i> ID объявления </i>
     * @return {@link CommentEntity}
     */
    private CommentEntity getById(int idComment, int idAd) {
        return commentRepository.findCommentEntityByIdAndAd_Id(idComment, idAd)
                .orElseThrow(CommentNotFoundException::new);
    }
}