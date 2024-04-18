package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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

    private final CommentRepository repository;
    private final AdService adService;
    private final UserService userService;
    private final CommentMapper mapper;

    @Override
    public ResponseEntity<CommentsDTO> getAllCommentsOfAd(int id) {
        AdEntity ad = adService.getById(id);

        List<CommentDTO> list = repository.findCommentEntitiesByAd(ad)
                .stream()
                .map(mapper::toCommentDTO)
                .collect(Collectors.toList());

        CommentsDTO dto = new CommentsDTO(list.size(), list);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<CommentDTO> addCommentToAd(int id, CreateOrUpdateCommentDTO dto,
                                                     Authentication auth) {

        UserEntity user = userService.getUser(auth.getName());
        AdEntity ad = adService.getById(id);
        CommentEntity comment = mapper.toCommentEntity(dto);

        comment.setCreatedAt(now());
        comment.setAd(ad);
        comment.setAuthor(user);

        repository.save(comment);
        return ResponseEntity.ok(mapper.toCommentDTO(comment));
    }

    @Override
    @PreAuthorize(value = "hasRole('ADMIN')" +
            "or @adServiceImpl.isAuthor(authentication.getName, #idAd)" +
            "or @commentServiceImpl.isAuthor(authentication.getName, #idComment)")
    public ResponseEntity<?> deleteComment(int idAd, int idComment) {
        AdEntity ad = adService.getById(idAd);
        List<CommentEntity> comments = repository.findCommentEntitiesByAd(ad);

        for (CommentEntity comment : comments) {

            if (comment.getId().equals(idComment)) {
                repository.delete(comment);
            }
            return ResponseEntity.ok().build();
        }
        throw new CommentNotFoundException();
    }

    @Override
    @PreAuthorize(value = "hasRole('ADMIN')" +
            "or @adServiceImpl.isAuthor(authentication.getName, #idAd)" +
            "or @commentServiceImpl.isAuthor(authentication.getName, #idComment)")
    public ResponseEntity<CommentDTO> updateComment(int idAd, int idComment,
                                                    CreateOrUpdateCommentDTO dto) {

        AdEntity ad = adService.getById(idAd);
        List<CommentEntity> comments = repository.findCommentEntitiesByAd(ad);

        for (CommentEntity comment : comments) {

            if (comment.getId().equals(idComment)) {
                comment.setText(dto.getText());
                comment.setCreatedAt(now());
                repository.save(comment);
            }
            return ResponseEntity.ok(mapper.toCommentDTO(comment));
        }
        throw new CommentNotFoundException();
    }

    public boolean isAuthor(String username, int id) {
        CommentEntity comment = repository.findById(id)
                .orElseThrow(CommentNotFoundException::new);
        return comment.getAuthor().getUsername().equals(username);
    }
}