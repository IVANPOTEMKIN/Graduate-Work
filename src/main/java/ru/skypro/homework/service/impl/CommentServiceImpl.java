package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.comment.CommentDTO;
import ru.skypro.homework.dto.comment.CommentsDTO;
import ru.skypro.homework.dto.comment.CreateOrUpdateCommentDTO;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

import static java.time.LocalDateTime.now;
import static ru.skypro.homework.mapper.CommentMapper.INSTANCE;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final AdService adService;
    private final UserService userService;

    @Override
    public CommentsDTO getAllCommentsOfAd(int id) {
        List<CommentDTO> dtoList = commentRepository.findCommentEntitiesByAd_Id(id)
                .stream()
                .map(INSTANCE::toCommentDTO)
                .collect(Collectors.toList());

        return new CommentsDTO(dtoList.size(), dtoList);
    }

    @Override
    public CommentDTO addCommentToAd(int id, CreateOrUpdateCommentDTO dto,
                                     Authentication auth) {

        UserEntity userEntity = userService.getUser(auth.getName());
        AdEntity adEntity = adService.getById(id);
        CommentEntity commentEntity = INSTANCE.toCommentEntity(dto);

        commentEntity.setCreatedAt(now());
        commentEntity.setAd(adEntity);
        commentEntity.setAuthor(userEntity);

        commentRepository.save(commentEntity);
        return INSTANCE.toCommentDTO(commentEntity);
    }

    @Override
    public void deleteComment(int idAd, int idComment) {
        List<CommentEntity> entities = commentRepository.findCommentEntitiesByAd_Id(idAd);

        for (CommentEntity comment : entities) {
            if (comment.getId().equals(idComment)) {
                commentRepository.delete(comment);
            }
        }
    }

    @Override
    public CommentDTO updateComment(int idAd, int idComment,
                                    CreateOrUpdateCommentDTO dto) {

        List<CommentEntity> entities = commentRepository.findCommentEntitiesByAd_Id(idAd);

        for (CommentEntity comment : entities) {
            if (comment.getId().equals(idComment)) {
                comment.setText(dto.getText());
                comment.setCreatedAt(now());
                commentRepository.save(comment);
                return INSTANCE.toCommentDTO(comment);
            }
        }
        return null;
    }

    @Override
    public CommentEntity getById(int id) {
        return commentRepository.findById(id).orElseThrow();
    }
}