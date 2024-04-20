package ru.skypro.homework.utils.mapper;

import ru.skypro.homework.dto.auth.RegisterDTO;
import ru.skypro.homework.dto.user.UpdateUserDTO;
import ru.skypro.homework.dto.user.UserDTO;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;

import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;
import static ru.skypro.homework.constants.URL.USER;
import static ru.skypro.homework.dto.Role.ADMIN;
import static ru.skypro.homework.utils.Constants.*;

public class UserMapperUtils {

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

    public static ImageEntity createImageEntity() {
        ImageEntity entity = new ImageEntity();

        entity.setId(ID);
        entity.setSize(1000L);
        entity.setType(IMAGE_PNG_VALUE);

        return entity;
    }

    public static RegisterDTO createDTO() {
        RegisterDTO dto = new RegisterDTO();

        dto.setUsername(USERNAME);
        dto.setPassword(PASSWORD);
        dto.setFirstName(FIRST_NAME);
        dto.setLastName(LAST_NAME);
        dto.setPhone(PHONE_NUMBER);
        dto.setRole(ADMIN);

        return dto;
    }

    public static UserDTO getUserDTO_From_UserEntity() {
        UserEntity entity = createUserEntity();
        UserDTO dto = new UserDTO();

        dto.setId(entity.getId());
        dto.setEmail(entity.getUsername());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setPhone(entity.getPhoneNumber());
        dto.setRole(entity.getRole());
        dto.setImage(USER.getUrl() + entity.getAvatar().getId());

        return dto;
    }

    public static UpdateUserDTO getUpdateUserDTO_From_UserEntity() {
        UserEntity entity = createUserEntity();
        UpdateUserDTO dto = new UpdateUserDTO();

        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setPhone(entity.getPhoneNumber());

        return dto;
    }

    public static UserEntity getUserEntity_From_RegisterDTO() {
        RegisterDTO dto = createDTO();
        UserEntity entity = new UserEntity();

        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPhoneNumber(dto.getPhone());
        entity.setRole(dto.getRole());

        return entity;
    }
}