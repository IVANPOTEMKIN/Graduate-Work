package ru.skypro.homework.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.skypro.homework.dto.comment.CommentDTO;
import ru.skypro.homework.dto.comment.CommentsDTO;
import ru.skypro.homework.dto.comment.CreateOrUpdateCommentDTO;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.exception.CommentNotFoundException;
import ru.skypro.homework.exception.user.UserNotFoundException;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.impl.CommentServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static ru.skypro.homework.utils.Constants.ID;
import static ru.skypro.homework.utils.Constants.USERNAME;
import static ru.skypro.homework.utils.Examples.*;

@ExtendWith({MockitoExtension.class, SpringExtension.class})
@ContextConfiguration
@WithMockUser(username = USERNAME, authorities = "ADMIN")
class CommentServiceImplTest {

    @Mock
    private CommentRepository repository;
    @Mock
    private AdService adService;
    @Mock
    private UserService userService;
    @Mock
    private CommentMapper mapper;
    @InjectMocks
    private CommentServiceImpl commentService;

    @BeforeEach
    void setUp() {
        commentService = new CommentServiceImpl(repository, adService, userService, mapper);
    }

    @Test
    void getAllCommentsOfAd_successful() {
        getAdById();

        when(repository.findCommentEntitiesByAd(any(AdEntity.class)))
                .thenReturn(List.of(createCommentEntity()));

        mapToCommentDTO();

        CommentsDTO expected = createCommentsDTO();
        CommentsDTO actual = commentService.getAllCommentsOfAd(anyInt());

        assertNotNull(actual);
        assertEquals(expected, actual);

        verify(adService, times(1))
                .getById(anyInt());
        verify(repository, times(1))
                .findCommentEntitiesByAd(any(AdEntity.class));
        verify(mapper, times(1))
                .toCommentDTO(any(CommentEntity.class));
    }

    @Test
    void getAllCommentsOfAd_AdNotFoundException() {
        getAdById_AdNotFoundException();

        assertThrows(AdNotFoundException.class,
                () -> commentService.getAllCommentsOfAd(anyInt()));

        verify(adService, times(1))
                .getById(anyInt());
        verify(repository, times(0))
                .findCommentEntitiesByAd(any(AdEntity.class));
        verify(mapper, times(0))
                .toCommentDTO(any(CommentEntity.class));
    }

    @Test
    void addCommentToAd_successful() {
        getUser();
        getAdById();

        when(mapper.toCommentEntity(any(CreateOrUpdateCommentDTO.class)))
                .thenReturn(createCommentEntity());

        mapToCommentDTO();

        CommentDTO expected = createCommentDTO();
        CommentDTO actual = commentService.addCommentToAd(ID, createCreateOrUpdateCommentDTO());

        assertNotNull(actual);
        assertEquals(expected, actual);

        verify(userService, times(1))
                .getUser();
        verify(adService, times(1))
                .getById(anyInt());
        verify(mapper, times(1))
                .toCommentEntity(any(CreateOrUpdateCommentDTO.class));
        verify(repository, times(1))
                .save(any(CommentEntity.class));
        verify((mapper), times(1))
                .toCommentDTO(any(CommentEntity.class));
    }

    @Test
    void addCommentToAd_UserNotFoundException() {
        when(userService.getUser())
                .thenThrow(UserNotFoundException.class);

        assertThrows(UserNotFoundException.class,
                () -> commentService.addCommentToAd(ID, createCreateOrUpdateCommentDTO()));

        verify(userService, times(1))
                .getUser();
        verify(adService, times(0))
                .getById(anyInt());
        verify(mapper, times(0))
                .toCommentEntity(any(CreateOrUpdateCommentDTO.class));
        verify(repository, times(0))
                .save(any(CommentEntity.class));
        verify((mapper), times(0))
                .toCommentDTO(any(CommentEntity.class));
    }

    @Test
    void addCommentToAd_AdNotFoundException() {
        getUser();
        getAdById_AdNotFoundException();

        assertThrows(AdNotFoundException.class,
                () -> commentService.addCommentToAd(ID, createCreateOrUpdateCommentDTO()));

        verify(userService, times(1))
                .getUser();
        verify(adService, times(1))
                .getById(anyInt());
        verify(mapper, times(0))
                .toCommentEntity(any(CreateOrUpdateCommentDTO.class));
        verify(repository, times(0))
                .save(any(CommentEntity.class));
        verify((mapper), times(0))
                .toCommentDTO(any(CommentEntity.class));
    }

    @Test
    void deleteComment_successful() {
        getCommentById();

        assertTrue(commentService.deleteComment(anyInt(), anyInt()));

        verify(repository, times(1))
                .findCommentEntityByIdAndAd_Id(anyInt(), anyInt());
        verify(repository, times(1))
                .delete(any(CommentEntity.class));
    }

    @Test
    void deleteComment_CommentNotFoundException() {
        assertThrows(CommentNotFoundException.class,
                () -> commentService.deleteComment(anyInt(), anyInt()));

        verify(repository, times(1))
                .findCommentEntityByIdAndAd_Id(anyInt(), anyInt());
        verify(repository, times(0))
                .delete(any(CommentEntity.class));
    }

    @Test
    void updateComment_successful() {
        getCommentById();
        mapToCommentDTO();

        CommentDTO expected = createCommentDTO();
        CommentDTO actual = commentService.updateComment(ID, ID, createCreateOrUpdateCommentDTO());

        assertNotNull(actual);
        assertEquals(expected, actual);

        verify(repository, times(1))
                .findCommentEntityByIdAndAd_Id(anyInt(), anyInt());
        verify(repository, times(1))
                .save(any(CommentEntity.class));
        verify(mapper, times(1))
                .toCommentDTO(any(CommentEntity.class));
    }

    @Test
    void updateComment_CommentNotFoundException() {
        assertThrows(CommentNotFoundException.class,
                () -> commentService.updateComment(ID, ID, createCreateOrUpdateCommentDTO()));

        verify(repository, times(1))
                .findCommentEntityByIdAndAd_Id(anyInt(), anyInt());
        verify(repository, times(0))
                .save(any(CommentEntity.class));
        verify(mapper, times(0))
                .toCommentDTO(any(CommentEntity.class));
    }

    private void getCommentById() {
        when(repository.findCommentEntityByIdAndAd_Id(anyInt(), anyInt()))
                .thenReturn(Optional.of(createCommentEntity()));
    }

    private void getUser() {
        when(userService.getUser())
                .thenReturn(createUserEntity());
    }

    private void getAdById() {
        when(adService.getById(anyInt()))
                .thenReturn(createAdEntity());
    }

    private void getAdById_AdNotFoundException() {
        when(adService.getById(anyInt()))
                .thenThrow(AdNotFoundException.class);
    }

    private void mapToCommentDTO() {
        when(mapper.toCommentDTO(any(CommentEntity.class)))
                .thenReturn(createCommentDTO());
    }
}