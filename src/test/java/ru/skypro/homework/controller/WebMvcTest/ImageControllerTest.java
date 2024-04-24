package ru.skypro.homework.controller.WebMvcTest;

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
import ru.skypro.homework.controller.ImageController;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.exception.ImageNotFoundException;
import ru.skypro.homework.exception.file.FilePathNotFoundException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.mapper.ImageMapper;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.impl.*;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.skypro.homework.utils.Constants.USERNAME;
import static ru.skypro.homework.utils.Examples.createFilePNG;
import static ru.skypro.homework.utils.Examples.createImageEntity;

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