package ru.skypro.homework.controller.WebMvcTest;

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
import ru.skypro.homework.controller.UserController;
import ru.skypro.homework.dto.auth.NewPasswordDTO;
import ru.skypro.homework.dto.user.UpdateUserDTO;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.file.FailedRecordFileException;
import ru.skypro.homework.exception.file.FailedSaveFileException;
import ru.skypro.homework.exception.password.ReusePasswordException;
import ru.skypro.homework.exception.password.WrongPasswordException;
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

import java.util.Optional;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.skypro.homework.constants.URL.USER;
import static ru.skypro.homework.utils.Constants.*;
import static ru.skypro.homework.utils.Examples.*;

@WebMvcTest
class UserControllerTest {

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
    private UserController imageController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @WithMockUser(username = USERNAME, authorities = "ADMIN")
    void updatePassword_successful() throws Exception {
        NewPasswordDTO dto = createNewPasswordDTO();
        UserEntity user = createUserEntity();

        getUser(user);

        when(encoder.matches(dto.getCurrentPassword(), user.getPassword()))
                .thenReturn(true);
        when(encoder.matches(dto.getNewPassword(), user.getPassword()))
                .thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users/set_password")
                        .with(csrf())
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void updatePassword_UnauthorizedException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users/set_password")
                        .with(csrf()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = USERNAME, authorities = "ADMIN")
    void updatePassword_ForbiddenException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users/set_password"))
                .andExpect(status().isForbidden());
    }

    @ParameterizedTest
    @MethodSource("paramsFor_UpdatePassword_test")
    @WithMockUser(username = USERNAME, authorities = "ADMIN")
    void updatePassword_ValidationException(NewPasswordDTO dto) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users/set_password")
                        .with(csrf())
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(username = USERNAME, authorities = "ADMIN")
    void updatePassword_UserNotFoundException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users/set_password")
                        .with(csrf())
                        .content(objectMapper.writeValueAsString(createNewPasswordDTO()))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string(new UserNotFoundException().getMessage()));
    }

    @Test
    @WithMockUser(username = USERNAME, authorities = "ADMIN")
    void updatePassword_WrongPasswordException() throws Exception {
        NewPasswordDTO dto = createNewPasswordDTO();
        UserEntity user = createUserEntity();

        getUser(user);

        when(encoder.matches(dto.getCurrentPassword(), user.getPassword()))
                .thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users/set_password")
                        .with(csrf())
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(new WrongPasswordException().getMessage()));
    }

    @Test
    @WithMockUser(username = USERNAME, authorities = "ADMIN")
    void updatePassword_ReusePasswordException() throws Exception {
        NewPasswordDTO dto = createNewPasswordDTO();
        UserEntity user = createUserEntity();

        getUser(user);

        when(encoder.matches(dto.getCurrentPassword(), user.getPassword()))
                .thenReturn(true);
        when(encoder.matches(dto.getNewPassword(), user.getPassword()))
                .thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users/set_password")
                        .with(csrf())
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(new ReusePasswordException().getMessage()));
    }

    @Test
    @WithMockUser(username = USERNAME, authorities = "ADMIN")
    void getInfoAboutUser_successful() throws Exception {
        UserEntity user = createUserEntity();

        getUser(user);

        when(userMapper.toUserDTO(any(UserEntity.class)))
                .thenReturn(createUserDTO());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/users/me"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(user.getId()))
                .andExpect(jsonPath("$.email").value(user.getUsername()))
                .andExpect(jsonPath("$.firstName").value(user.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(user.getLastName()))
                .andExpect(jsonPath("$.phone").value(user.getPhoneNumber()))
                .andExpect(jsonPath("$.role").value(user.getRole().toString()))
                .andExpect(jsonPath("$.image").value(USER.getUrl() + user.getAvatar().getId()));
    }

    @Test
    void getInfoAboutUser_UnauthorizedException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/users/me"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = USERNAME, authorities = "ADMIN")
    void getInfoAboutUser_UserNotFoundException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/users/me"))
                .andExpect(status().isNotFound())
                .andExpect(content().string(new UserNotFoundException().getMessage()));
    }

    @Test
    @WithMockUser(username = USERNAME, authorities = "ADMIN")
    void updateInfoAboutUser_successful() throws Exception {
        UserEntity user = createUserEntity();

        getUser(user);

        when(userMapper.toUpdateUserDTO(any(UserEntity.class)))
                .thenReturn(createUpdateUserDTO());

        mockMvc.perform(MockMvcRequestBuilders
                        .patch("/users/me")
                        .with(csrf())
                        .content(objectMapper.writeValueAsString(createUpdateUserDTO()))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(user.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(user.getLastName()))
                .andExpect(jsonPath("$.phone").value(user.getPhoneNumber()));
    }

    @Test
    void updateInfoAboutUser_UnauthorizedException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .patch("/users/me")
                        .with(csrf()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = USERNAME, authorities = "ADMIN")
    void updateInfoAboutUser_ForbiddenException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .patch("/users/me"))
                .andExpect(status().isForbidden());
    }

    @ParameterizedTest
    @MethodSource("paramsFor_UpdateInfoAboutUser_test")
    @WithMockUser(username = USERNAME, authorities = "ADMIN")
    void updateInfoAboutUser_ValidationException(UpdateUserDTO dto) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .patch("/users/me")
                        .with(csrf())
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(username = USERNAME, authorities = "ADMIN")
    void updateInfoAboutUser_UserNotFoundException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .patch("/users/me")
                        .with(csrf())
                        .content(objectMapper.writeValueAsString(createUpdateUserDTO()))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string(new UserNotFoundException().getMessage()));
    }

    @Test
    @WithMockUser(username = USERNAME, authorities = "ADMIN")
    void updateAvatarOfUser_successful() throws Exception {
        getUser(createUserEntity());

        ImageEntity image = createImageEntity();

        when(imageMapper.toImageEntity(any(MultipartFile.class)))
                .thenReturn(image);
        when(imageRepository.save(any(ImageEntity.class)))
                .thenReturn(image);

        mockMvc.perform(MockMvcRequestBuilders
                        .multipart("/users/me/image")
                        .file("image", createFilePNG().getBytes())
                        .with(csrf())
                        .with(request -> {
                            request.setMethod("PATCH");
                            return request;
                        }))
                .andExpect(status().isOk());
    }

    @Test
    void updateAvatarOfUser_UnauthorizedException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .multipart("/users/me/image")
                        .file("image", createFilePNG().getBytes())
                        .with(csrf())
                        .with(request -> {
                            request.setMethod("PATCH");
                            return request;
                        }))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = USERNAME, authorities = "ADMIN")
    void updateAvatarOfUser_ForbiddenException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .multipart("/users/me/image")
                        .file("image", createFilePNG().getBytes())
                        .with(request -> {
                            request.setMethod("PATCH");
                            return request;
                        }))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = USERNAME, authorities = "ADMIN")
    void updateAvatarOfUser_UserNotFoundException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .multipart("/users/me/image")
                        .file("image", createFilePNG().getBytes())
                        .with(csrf())
                        .with(request -> {
                            request.setMethod("PATCH");
                            return request;
                        }))
                .andExpect(status().isNotFound())
                .andExpect(content().string(new UserNotFoundException().getMessage()));
    }

    @Test
    @WithMockUser(username = USERNAME, authorities = "ADMIN")
    void updateAvatarOfUser_FailedRecordFileException() throws Exception {
        getUser(createUserEntity());

        when(imageMapper.toImageEntity(any(MultipartFile.class)))
                .thenThrow(FailedRecordFileException.class);

        mockMvc.perform(MockMvcRequestBuilders
                        .multipart("/users/me/image")
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
    void updateAvatarOfUser_FailedSaveFileException() throws Exception {
        getUser(createUserEntity());

        when(imageMapper.toImageEntity(any(MultipartFile.class)))
                .thenReturn(createImageEntity());
        when(imageRepository.save(any(ImageEntity.class)))
                .thenThrow(FailedSaveFileException.class);

        mockMvc.perform(MockMvcRequestBuilders
                        .multipart("/users/me/image")
                        .file("image", createFilePNG().getBytes())
                        .with(csrf())
                        .with(request -> {
                            request.setMethod("PATCH");
                            return request;
                        }))
                .andExpect(status().isExpectationFailed());
    }

    private static Stream<Arguments> paramsFor_UpdatePassword_test() {
        return Stream.of(
                Arguments.of(new NewPasswordDTO(PASSWORD, "")),
                Arguments.of(new NewPasswordDTO("", NEW_PASSWORD))
        );
    }

    private static Stream<Arguments> paramsFor_UpdateInfoAboutUser_test() {
        return Stream.of(
                Arguments.of(new UpdateUserDTO("", LAST_NAME, PHONE_NUMBER)),
                Arguments.of(new UpdateUserDTO(FIRST_NAME, "", PHONE_NUMBER)),
                Arguments.of(new UpdateUserDTO(FIRST_NAME, LAST_NAME, ""))
        );
    }

    private void getUser(UserEntity user) {
        when(userRepository.findUserEntityByUsername(anyString()))
                .thenReturn(Optional.of(user));
    }
}