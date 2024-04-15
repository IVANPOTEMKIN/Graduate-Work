package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.comment.CommentDTO;
import ru.skypro.homework.dto.comment.CommentsDTO;
import ru.skypro.homework.dto.comment.CreateOrUpdateCommentDTO;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repository;
    private final AdService adService;
    private final UserService userService;
    private final CommentMapper mapper;

    @Override
    public ResponseEntity<CommentsDTO> getAllCommentsOfAd(int id) {
        List<CommentDTO> list = repository.findCommentEntitiesByAd_Id(id)
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
    public ResponseEntity<?> deleteComment(int idAd, int idComment) {
        List<CommentEntity> comments = repository.findCommentEntitiesByAd_Id(idAd);

        for (CommentEntity comment : comments) {
            if (comment.getId().equals(idComment)) {
                repository.delete(comment);
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.status(NOT_FOUND).build();
    }

    @Override
    public ResponseEntity<CommentDTO> updateComment(int idAd, int idComment,
                                                    CreateOrUpdateCommentDTO dto) {

        List<CommentEntity> comments = repository.findCommentEntitiesByAd_Id(idAd);

        for (CommentEntity comment : comments) {
            if (comment.getId().equals(idComment)) {
                comment.setText(dto.getText());
                comment.setCreatedAt(now());
                repository.save(comment);
                return ResponseEntity.ok(mapper.toCommentDTO(comment));
            }
        }
        return ResponseEntity.status(NOT_FOUND).build();
    }
}