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
        AdEntity adEntity = adService.getById(id);

        List<CommentDTO> dtoList = adEntity.getComments().stream()
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
        AdEntity adEntity = adService.getById(idAd);
        CommentEntity commentEntity = getById(idComment);

        for (CommentEntity comment : adEntity.getComments()) {
            if (comment.equals(commentEntity)) {
                commentRepository.delete(comment);
            }
        }
    }

    @Override
    public CommentDTO updateComment(int idAd, int idComment,
                                    CreateOrUpdateCommentDTO dto) {

        AdEntity adEntity = adService.getById(idAd);
        CommentEntity commentEntity = getById(idComment);

        for (CommentEntity comment : adEntity.getComments()) {
            if (comment.equals(commentEntity)) {
                comment.setText(dto.getText());
                commentRepository.save(comment);
            }
        }

        return INSTANCE.toCommentDTO(commentEntity);
    }

    @Override
    public CommentEntity getById(int id) {
        return commentRepository.findById(id).orElseThrow();
    }
}