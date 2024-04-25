package ru.skypro.homework.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.skypro.homework.dto.comment.CreateOrUpdateCommentDTO;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.exception.CommentNotFoundException;
import ru.skypro.homework.exception.user.UserNotFoundException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.mapper.ImageMapper;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.impl.*;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.skypro.homework.constants.URL.USER;
import static ru.skypro.homework.utils.Constants.ID;
import static ru.skypro.homework.utils.Constants.USERNAME;
import static ru.skypro.homework.utils.Examples.*;

@WebMvcTest
class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AdMapper adMapper;
    @MockBean
    private CommentMapper commentMapper;
    @MockBean
    private ImageMapper imageMapper;
    @MockBean
    private UserMapper userMapper;
    @MockBean
    private AdRepository adRepository;
    @MockBean
    private CommentRepository commentRepository;
    @MockBean
    private ImageRepository imageRepository;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private PasswordEncoder encoder;
    @SpyBean
    private AdServiceImpl adService;
    @SpyBean
    private AuthServiceImpl authService;
    @SpyBean
    private CommentServiceImpl commentService;
    @SpyBean
    private ImageServiceImpl imageService;
    @SpyBean
    private UserDetailsServiceImpl detailsService;
    @SpyBean
    private UserServiceImpl userService;
    @InjectMocks
    private CommentController commentController;

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    // getAllCommentsOfAd

    @Test
    @WithMockUser(username = USERNAME, authorities = "ADMIN")
    void getAllCommentsOfAd_successful() throws Exception {
        getAd();

        when(commentRepository.findCommentEntitiesByAd(any(AdEntity.class)))
                .thenReturn(List.of(createCommentEntity()));

        mapToCommentDTO();

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/ads/{ID}/comments", ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value(createCommentsDTO().getCount()));
    }

    @Test
    void getAllCommentsOfAd_UnauthorizedException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/ads/{ID}/comments", ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = USERNAME, authorities = "ADMIN")
    void getAllCommentsOfAd_AdNotFoundException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/ads/{ID}/comments", anyInt()))
                .andExpect(status().isNotFound())
                .andExpect(content().string(new AdNotFoundException().getMessage()));
    }

    // addCommentToAd

    @Test
    @WithMockUser(username = USERNAME, authorities = "ADMIN")
    void addCommentToAd_successful() throws Exception {
        CommentEntity comment = createCommentEntity();

        getUser();
        getAd();

        when(commentMapper.toCommentEntity(any(CreateOrUpdateCommentDTO.class)))
                .thenReturn(comment);

        mapToCommentDTO();

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/ads/{ID}/comments", ID)
                        .content(objectMapper.writeValueAsString(createCreateOrUpdateCommentDTO()))
                        .contentType(APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.author").value(comment.getAuthor().getId()))
                .andExpect(jsonPath("$.authorImage").value(USER.getUrl() + comment.getAuthor().getAvatar().getId()))
                .andExpect(jsonPath("$.authorFirstName").value(comment.getAuthor().getFirstName()))
                .andExpect(jsonPath("$.pk").value(comment.getId()))
                .andExpect(jsonPath("$.text").value(comment.getText()));
    }

    @Test
    void addCommentToAd_UnauthorizedException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/ads/{ID}/comments", ID)
                        .with(csrf()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = USERNAME, authorities = "ADMIN")
    void addCommentToAd_ForbiddenException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/ads/{ID}/comments", ID))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = USERNAME, authorities = "ADMIN")
    void addCommentToAd_ValidationException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/ads/{ID}/comments", ID)
                        .content(objectMapper.writeValueAsString(new CreateOrUpdateCommentDTO("")))
                        .contentType(APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(username = USERNAME, authorities = "ADMIN")
    void addCommentToAd_UserNotFoundException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/ads/{ID}/comments", ID)
                        .content(objectMapper.writeValueAsString(createCreateOrUpdateCommentDTO()))
                        .contentType(APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().isNotFound())
                .andExpect(content().string(new UserNotFoundException().getMessage()));
    }

    @Test
    @WithMockUser(username = USERNAME, authorities = "ADMIN")
    void addCommentToAd_AdNotFoundException() throws Exception {
        getUser();

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/ads/{ID}/comments", ID)
                        .content(objectMapper.writeValueAsString(createCreateOrUpdateCommentDTO()))
                        .contentType(APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().isNotFound())
                .andExpect(content().string(new AdNotFoundException().getMessage()));
    }

    // deleteComment

    @Test
    @WithMockUser(username = USERNAME, authorities = "ADMIN")
    void deleteComment_successful() throws Exception {
        getComment(createCommentEntity());

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/ads/{ID}/comments/{ID}", ID, ID)
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    void deleteComment_UnauthorizedException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/ads/{ID}/comments/{ID}", ID, ID)
                        .with(csrf()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = USERNAME, authorities = "ADMIN")
    void deleteComment_ForbiddenException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/ads/{ID}/comments/{ID}", ID, ID))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = USERNAME, authorities = "ADMIN")
    void deleteComment_CommentNotFoundException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/ads/{ID}/comments/{ID}", ID, ID)
                        .with(csrf()))
                .andExpect(status().isNotFound())
                .andExpect(content().string(new CommentNotFoundException().getMessage()));
    }

    // updateComment

    @Test
    @WithMockUser(username = USERNAME, authorities = "ADMIN")
    void updateComment_successful() throws Exception {
        CommentEntity comment = createCommentEntity();

        getComment(comment);
        mapToCommentDTO();

        mockMvc.perform(MockMvcRequestBuilders
                        .patch("/ads/{ID}/comments/{ID}", ID, ID)
                        .with(csrf())
                        .content(objectMapper.writeValueAsString(createCreateOrUpdateCommentDTO()))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.author").value(comment.getAuthor().getId()))
                .andExpect(jsonPath("$.authorImage").value(USER.getUrl() + comment.getAuthor().getAvatar().getId()))
                .andExpect(jsonPath("$.authorFirstName").value(comment.getAuthor().getFirstName()))
                .andExpect(jsonPath("$.pk").value(comment.getId()))
                .andExpect(jsonPath("$.text").value(comment.getText()));
    }

    @Test
    void updateComment_UnauthorizedException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .patch("/ads/{ID}/comments/{ID}", ID, ID)
                        .with(csrf()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = USERNAME, authorities = "ADMIN")
    void updateComment_ForbiddenException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .patch("/ads/{ID}/comments/{ID}", ID, ID))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = USERNAME, authorities = "ADMIN")
    void updateComment_ValidationException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .patch("/ads/{ID}/comments/{ID}", ID, ID)
                        .with(csrf())
                        .content(objectMapper.writeValueAsString(new CreateOrUpdateCommentDTO("")))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(username = USERNAME, authorities = "ADMIN")
    void updateComment_CommentNotFoundException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .patch("/ads/{ID}/comments/{ID}", ID, ID)
                        .with(csrf())
                        .content(objectMapper.writeValueAsString(createCreateOrUpdateCommentDTO()))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string(new CommentNotFoundException().getMessage()));
    }

    // Utils

    private void getComment(CommentEntity comment) {
        when(commentRepository.findCommentEntityByIdAndAd_Id(anyInt(), anyInt()))
                .thenReturn(Optional.of(comment));
    }

    private void getAd() {
        when(adRepository.findById(anyInt()))
                .thenReturn(Optional.of(createAdEntity()));
    }

    private void getUser() {
        when(userRepository.findUserEntityByUsername(anyString()))
                .thenReturn(Optional.of(createUserEntity()));
    }

    private void mapToCommentDTO() {
        when(commentMapper.toCommentDTO(any(CommentEntity.class)))
                .thenReturn(createCommentDTO());
    }
}