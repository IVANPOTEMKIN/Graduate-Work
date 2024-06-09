package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.rq.comment.Comment;
import ru.skypro.homework.dto.rq.comment.CreateOrUpdateComment;
import ru.skypro.homework.dto.rs.comment.Comments;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private final UserService userService;
    private final AdService adService;
    private final AdEntity ad;
    private final CommentRepository commentRepository;
    private final AdRepository adRepository;
    private final CommentMapper mapper;

    public CommentServiceImpl(UserService userService, AdService adService, AdEntity ad, CommentRepository commentRepository, AdRepository adRepository, CommentMapper commentMapper) {
        this.userService = userService;
        this.adService = adService;
        this.ad = ad;
        this.commentRepository = commentRepository;
        this.adRepository = adRepository;
        this.mapper = commentMapper;
    }

    @Override
    public Comments getCommentsByAd(Integer id) {
        Optional<AdEntity> ad = adRepository.findById(id);
        if (ad.isPresent()) {
            List<Comment> comments = ad.get().getComments().stream().map(mapper::entityToComment).collect(Collectors.toList());
            return new Comments(comments.size(), comments);
        }
        throw new AdNotFoundException("Ad not found");
    }

    @Override
    public Comment postComment(Integer id, CreateOrUpdateComment createOrUpdateComment) {
        UserEntity user = userService.getCurrentUser();
        AdEntity adEntity = adService.getAdById(id);
        CommentEntity commentEntity = mapper.createOrUpdateCommentToEntity(createOrUpdateComment);
        commentEntity.setAuthor(user).setAd(adEntity);
        commentRepository.save(commentEntity);
        return mapper.entityToComment(commentEntity);
    }

    @Override
    public Comment deleteComment(Integer id) {


    }

    @Override
    public Comment updateComment(Integer id) {

    }


    private CommentEntity getCommentId(Integer id) {
        return commentRepository.getById(id);
    }


}
