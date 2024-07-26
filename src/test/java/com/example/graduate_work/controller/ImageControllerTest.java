package com.example.graduate_work.controller;

import com.example.graduate_work.entity.ImageEntity;
import com.example.graduate_work.exception.ImageNotFoundException;
import com.example.graduate_work.exception.file.FilePathNotFoundException;
import com.example.graduate_work.mapper.AdMapper;
import com.example.graduate_work.mapper.CommentMapper;
import com.example.graduate_work.mapper.ImageMapper;
import com.example.graduate_work.mapper.UserMapper;
import com.example.graduate_work.repository.AdRepository;
import com.example.graduate_work.repository.CommentRepository;
import com.example.graduate_work.repository.ImageRepository;
import com.example.graduate_work.repository.UserRepository;
import com.example.graduate_work.service.impl.*;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static com.example.graduate_work.utils.Constants.USERNAME;
import static com.example.graduate_work.utils.Examples.createFilePNG;
import static com.example.graduate_work.utils.Examples.createImageEntity;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@WithMockUser(username = USERNAME, authorities = "ADMIN")
class ImageControllerTest {

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
    private ImageController imageController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    // getAvatar

    @Test
    void getAvatar_success() throws Exception {
        getImage(saveImage());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/images/user/" + anyInt()))
                .andExpect(status().isOk());
    }

    @Test
    void getAvatar_ImageNotFoundException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/images/user/" + anyInt()))
                .andExpect(status().isNotFound())
                .andExpect(content().string(new ImageNotFoundException().getMessage()));
    }

    @Test
    void getAvatar_FilePathNotFoundException() throws Exception {
        getImage(createImageEntity());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/images/user/" + anyInt()))
                .andExpect(status().isNotFound())
                .andExpect(content().string(new FilePathNotFoundException().getMessage()));
    }

    // getImage

    @Test
    void getImage_success() throws Exception {
        getImage(saveImage());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/images/ad/" + anyInt()))
                .andExpect(status().isOk());
    }

    @Test
    void getImage_ImageNotFoundException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/images/ad/" + anyInt()))
                .andExpect(status().isNotFound())
                .andExpect(content().string(new ImageNotFoundException().getMessage()));
    }

    @Test
    void getImage_FilePathNotFoundException() throws Exception {
        getImage(createImageEntity());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/images/ad/" + anyInt()))
                .andExpect(status().isNotFound())
                .andExpect(content().string(new FilePathNotFoundException().getMessage()));
    }

    // Utils

    private void getImage(ImageEntity image) {
        when(imageRepository.findById(anyInt()))
                .thenReturn(Optional.of(image));
    }

    private ImageEntity saveImage() {
        ImageEntity image = createImageEntity();

        when(imageMapper.toImageEntity(any(MultipartFile.class)))
                .thenReturn(image);

        imageService.saveImage(createFilePNG());
        return image;
    }
}