package com.example.graduate_work.controller;

import com.example.graduate_work.dto.ad.CreateOrUpdateAdDTO;
import com.example.graduate_work.entity.AdEntity;
import com.example.graduate_work.entity.ImageEntity;
import com.example.graduate_work.entity.UserEntity;
import com.example.graduate_work.exception.AdNotFoundException;
import com.example.graduate_work.exception.ImageNotFoundException;
import com.example.graduate_work.exception.file.FailedRecordFileException;
import com.example.graduate_work.exception.file.FailedSaveFileException;
import com.example.graduate_work.exception.file.FilePathNotFoundException;
import com.example.graduate_work.exception.user.UserNotFoundException;
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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.example.graduate_work.utils.Constants.ID;
import static com.example.graduate_work.utils.Constants.USERNAME;
import static com.example.graduate_work.utils.Examples.*;
import static com.example.graduate_work.utils.URL.AD;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class AdControllerTest {

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
    private AdController adController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    // getAllAds

    @Test
    @WithMockUser(username = USERNAME, authorities = "ADMIN")
    void getAllAds_successful() throws Exception {
        when(adRepository.findAll())
                .thenReturn(List.of(createAdEntity()));

        mapToAdDTO();

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/ads"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(createAdsDTO())));
    }

    @Test
    void getAllAds_UnauthorizedException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/ads"))
                .andExpect(status().isUnauthorized());
    }

    // addAd

    @Test
    void addAd_UnauthorizedException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/ads")
                        .with(csrf()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = USERNAME, authorities = "ADMIN")
    void addAd_ForbiddenException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/ads"))
                .andExpect(status().isForbidden());
    }

    // getInfoAboutAd

    @Test
    @WithMockUser(username = USERNAME, authorities = "ADMIN")
    void getInfoAboutAd_successful() throws Exception {
        AdEntity ad = createAdEntity();

        getAd(ad);

        when(adMapper.toExtendedAdDTO(any(AdEntity.class)))
                .thenReturn(createExtendedAdDTO());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/ads/" + anyInt()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pk").value(ad.getId()))
                .andExpect(jsonPath("$.authorFirstName").value(ad.getAuthor().getFirstName()))
                .andExpect(jsonPath("$.authorLastName").value(ad.getAuthor().getLastName()))
                .andExpect(jsonPath("$.description").value(ad.getDescription()))
                .andExpect(jsonPath("$.email").value(ad.getAuthor().getUsername()))
                .andExpect(jsonPath("$.image").value(AD.getUrl() + ad.getImage().getId()))
                .andExpect(jsonPath("$.phone").value(ad.getAuthor().getPhoneNumber()))
                .andExpect(jsonPath("$.price").value(ad.getPrice()))
                .andExpect(jsonPath("$.title").value(ad.getTitle()));
    }

    @Test
    @WithMockUser(username = USERNAME, authorities = "ADMIN")
    void getInfoAboutAd_AdNotFoundException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/ads/" + anyInt()))
                .andExpect(status().isNotFound())
                .andExpect(content().string(new AdNotFoundException().getMessage()));
    }

    // deleteAd

    @Test
    @WithMockUser(username = USERNAME, authorities = "ADMIN")
    void deleteAd_successful() throws Exception {
        ImageEntity image = createImageEntity();

        mapToImage(image);

        imageService.saveImage(createFilePNG());

        getAd(createAdEntity());
        getImage(image);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/ads/" + anyInt())
                        .with(csrf()))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(username = USERNAME, authorities = "ADMIN")
    void deleteAd_AdNotFoundException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/ads/" + anyInt())
                        .with(csrf()))
                .andExpect(status().isNotFound())
                .andExpect(content().string(new AdNotFoundException().getMessage()));
    }

    @Test
    @WithMockUser(username = USERNAME, authorities = "ADMIN")
    void deleteAd_FilePathNotFoundException() throws Exception {
        ImageEntity image = createImageEntity();

        mapToImage(image);
        getAd(createAdEntity());
        getImage(image);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/ads/" + anyInt())
                        .with(csrf()))
                .andExpect(status().isNotFound())
                .andExpect(content().string(new FilePathNotFoundException().getMessage()));
    }

    @Test
    @WithMockUser(username = USERNAME, authorities = "ADMIN")
    void deleteAd_ImageNotFoundException() throws Exception {
        getAd(createAdEntity());

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/ads/" + anyInt())
                        .with(csrf()))
                .andExpect(status().isNotFound())
                .andExpect(content().string(new ImageNotFoundException().getMessage()));
    }

    // updateInfoAboutAd

    @Test
    @WithMockUser(username = USERNAME, authorities = "ADMIN")
    void updateInfoAboutAd_successful() throws Exception {
        AdEntity ad = createAdEntity();

        getAd(ad);
        mapToAdDTO();

        mockMvc.perform(MockMvcRequestBuilders
                        .patch("/ads/" + ID)
                        .content(objectMapper.writeValueAsBytes(createCreateOrUpdateAdDTO()))
                        .contentType(APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.author").value(ad.getAuthor().getId()))
                .andExpect(jsonPath("$.image").value(AD.getUrl() + ad.getImage().getId()))
                .andExpect(jsonPath("$.pk").value(ad.getId()))
                .andExpect(jsonPath("$.price").value(ad.getPrice()))
                .andExpect(jsonPath("$.title").value(ad.getTitle()));
    }

    @ParameterizedTest
    @MethodSource("paramsFor_UpdateInfoAboutAd_test")
    @WithMockUser(username = USERNAME, authorities = "ADMIN")
    void updateInfoAboutAd_ValidationException(CreateOrUpdateAdDTO dto) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .patch("/ads/" + ID)
                        .content(objectMapper.writeValueAsBytes(dto))
                        .contentType(APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(username = USERNAME, authorities = "ADMIN")
    void updateInfoAboutAd_AdNotFoundException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .patch("/ads/" + ID)
                        .content(objectMapper.writeValueAsBytes(createCreateOrUpdateAdDTO()))
                        .contentType(APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().isNotFound())
                .andExpect(content().string(new AdNotFoundException().getMessage()));
    }

    // getAllAdsOfUser

    @Test
    @WithMockUser(username = USERNAME, authorities = "ADMIN")
    void getAllAdsOfUser_successful() throws Exception {
        when(userRepository.findUserEntityByUsername(anyString()))
                .thenReturn(Optional.of(createUserEntity()));
        when(adRepository.findAdEntitiesByAuthor(any(UserEntity.class)))
                .thenReturn(List.of(createAdEntity()));

        mapToAdDTO();

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/ads/me"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(createAdsDTO())));
    }

    @Test
    void getAllAdsOfUser_UnauthorizedException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/ads/me"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = USERNAME, authorities = "ADMIN")
    void getAllAdsOfUser_UserNotFoundException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/ads/me"))
                .andExpect(status().isNotFound())
                .andExpect(content().string(new UserNotFoundException().getMessage()));
    }

    // updateImageOfAd

    @Test
    @WithMockUser(username = USERNAME, authorities = "ADMIN")
    void updateImageOfAd_success() throws Exception {
        getAd(createAdEntity());

        ImageEntity image = createImageEntity();

        mapToImage(image);

        when(imageRepository.save(any(ImageEntity.class)))
                .thenReturn(image);

        mockMvc.perform(MockMvcRequestBuilders
                        .multipart("/ads/{ID}/image", ID)
                        .file("image", createFilePNG().getBytes())
                        .with(csrf())
                        .with(request -> {
                            request.setMethod("PATCH");
                            return request;
                        }))
                .andExpect(status().isOk())
                .andExpect(content().string(image.getPath()));
    }

    @Test
    @WithMockUser(username = USERNAME, authorities = "ADMIN")
    void updateImageOfAd_AdNotFoundException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .multipart("/ads/{ID}/image", ID)
                        .file("image", createFilePNG().getBytes())
                        .with(csrf())
                        .with(request -> {
                            request.setMethod("PATCH");
                            return request;
                        }))
                .andExpect(status().isNotFound())
                .andExpect(content().string(new AdNotFoundException().getMessage()));
    }

    @Test
    @WithMockUser(username = USERNAME, authorities = "ADMIN")
    void updateImageOfAd_FailedRecordFileException() throws Exception {
        getAd(createAdEntity());

        when(imageMapper.toImageEntity(any(MultipartFile.class)))
                .thenThrow(FailedRecordFileException.class);

        mockMvc.perform(MockMvcRequestBuilders
                        .multipart("/ads/{ID}/image", ID)
                        .file("image", createFilePNG().getBytes())
                        .with(csrf())
                        .with(request -> {
                            request.setMethod("PATCH");
                            return request;
                        }))
                .andExpect(status().isExpectationFailed());
    }

    @Test
    @WithMockUser(username = USERNAME, authorities = "ADMIN")
    void updateImageOfAd_FailedSaveFileException() throws Exception {
        getAd(createAdEntity());
        mapToImage(createImageEntity());

        when(imageRepository.save(any(ImageEntity.class)))
                .thenThrow(FailedSaveFileException.class);

        mockMvc.perform(MockMvcRequestBuilders
                        .multipart("/ads/{ID}/image", ID)
                        .file("image", createFilePNG().getBytes())
                        .with(csrf())
                        .with(request -> {
                            request.setMethod("PATCH");
                            return request;
                        }))
                .andExpect(status().isExpectationFailed());
    }

    // Utils

    private static Stream<Arguments> paramsFor_UpdateInfoAboutAd_test() {
        return Stream.of(
                Arguments.of(new CreateOrUpdateAdDTO("", -1, "")),
                Arguments.of(new CreateOrUpdateAdDTO("", 10_000_001, "")),
                Arguments.of(new CreateOrUpdateAdDTO("", 1000, "description")),
                Arguments.of(new CreateOrUpdateAdDTO("title_ad_1", 1000, ""))
        );
    }

    private void getAd(AdEntity ad) {
        when(adRepository.findById(anyInt()))
                .thenReturn(Optional.of(ad));
    }

    private void getImage(ImageEntity image) {
        when(imageRepository.findById(anyInt()))
                .thenReturn(Optional.of(image));
    }

    private void mapToAdDTO() {
        when(adMapper.toAdDTO(any(AdEntity.class)))
                .thenReturn(createAdDTO());
    }

    private void mapToImage(ImageEntity image) {
        when(imageMapper.toImageEntity(any(MultipartFile.class)))
                .thenReturn(image);
    }
}