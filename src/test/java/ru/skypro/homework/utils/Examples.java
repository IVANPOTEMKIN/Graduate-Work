package ru.skypro.homework.utils;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ad.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.auth.LoginDTO;
import ru.skypro.homework.dto.auth.NewPasswordDTO;
import ru.skypro.homework.dto.auth.RegisterDTO;
import ru.skypro.homework.dto.comment.CreateOrUpdateCommentDTO;
import ru.skypro.homework.dto.user.UpdateUserDTO;
import ru.skypro.homework.dto.user.UserDTO;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;

import static java.time.LocalDateTime.MIN;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;
import static ru.skypro.homework.constants.URL.USER;
import static ru.skypro.homework.dto.Role.ADMIN;
import static ru.skypro.homework.utils.Constants.*;

public class Examples {

    // ENTITIES

    public static ImageEntity createImageEntity() {
        ImageEntity entity = new ImageEntity();

        entity.setId(ID);
        entity.setPath("path");
        entity.setSize(1000L);
        entity.setType(IMAGE_PNG_VALUE);
        entity.setData("bytes".getBytes());

        return entity;
    }

    public static UserEntity createUserEntity() {
        UserEntity entity = new UserEntity();

        entity.setId(ID);
        entity.setUsername(USERNAME);
        entity.setPassword(PASSWORD);
        entity.setFirstName(FIRST_NAME);
        entity.setLastName(LAST_NAME);
        entity.setPhoneNumber(PHONE_NUMBER);
        entity.setRole(ADMIN);
        entity.setAvatar(createImageEntity());

        return entity;
    }

    public static AdEntity createAdEntity() {
        AdEntity entity = new AdEntity();

        entity.setId(ID);
        entity.setTitle(TITLE);
        entity.setPrice(PRICE);
        entity.setDescription(DESCRIPTION);
        entity.setImage(createImageEntity());
        entity.setAuthor(createUserEntity());

        return entity;
    }

    public static CommentEntity createCommentEntity() {
        CommentEntity entity = new CommentEntity();

        entity.setId(ID);
        entity.setCreatedAt(MIN);
        entity.setText(TEXT);
        entity.setAd(createAdEntity());
        entity.setAuthor(createUserEntity());

        return entity;
    }

    // DTOs

    public static RegisterDTO createRegisterDTO() {
        RegisterDTO dto = new RegisterDTO();

        dto.setUsername(USERNAME);
        dto.setPassword(PASSWORD);
        dto.setFirstName(FIRST_NAME);
        dto.setLastName(LAST_NAME);
        dto.setPhone(PHONE_NUMBER);
        dto.setRole(ADMIN);

        return dto;
    }

    public static LoginDTO createLoginDTO() {
        LoginDTO dto = new LoginDTO();

        dto.setUsername(USERNAME);
        dto.setPassword(PASSWORD);

        return dto;
    }

    public static NewPasswordDTO createNewPasswordDTO() {
        NewPasswordDTO dto = new NewPasswordDTO();

        dto.setCurrentPassword(PASSWORD);
        dto.setNewPassword(NEW_PASSWORD);

        return dto;
    }

    public static UserDTO createUserDTO() {
        UserDTO dto = new UserDTO();

        dto.setId(ID);
        dto.setEmail(USERNAME);
        dto.setFirstName(FIRST_NAME);
        dto.setLastName(LAST_NAME);
        dto.setPhone(PHONE_NUMBER);
        dto.setRole(ADMIN);
        dto.setImage(USER.getUrl() + ID);

        return dto;
    }

    public static UpdateUserDTO createUpdateUserDTO() {
        UpdateUserDTO dto = new UpdateUserDTO();

        dto.setFirstName(NEW_FIRST_NAME);
        dto.setLastName(NEW_LAST_NAME);
        dto.setPhone(NEW_PHONE_NUMBER);

        return dto;
    }

    public static CreateOrUpdateAdDTO createCreateOrUpdateAdDTO() {
        CreateOrUpdateAdDTO dto = new CreateOrUpdateAdDTO();

        dto.setTitle(TITLE);
        dto.setPrice(PRICE);
        dto.setDescription(DESCRIPTION);

        return dto;
    }

    public static CreateOrUpdateCommentDTO createCreateOrUpdateCommentDTO() {
        CreateOrUpdateCommentDTO dto = new CreateOrUpdateCommentDTO();

        dto.setText(TEXT);

        return dto;
    }

    // OTHER

    public static MultipartFile createFilePNG() {
        return new MockMultipartFile(
                "file",
                "image.png",
                IMAGE_PNG_VALUE,
                "bytes".getBytes()
        );
    }

    public static UserDetails createUserDetails() {
        return User.builder()
                .username(USERNAME)
                .password(PASSWORD)
                .roles(ADMIN.name())
                .build();
    }
}