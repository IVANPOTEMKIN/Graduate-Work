package ru.skypro.homework.utils.mapper;

import ru.skypro.homework.dto.auth.RegisterDTO;
import ru.skypro.homework.dto.user.UpdateUserDTO;
import ru.skypro.homework.dto.user.UserDTO;
import ru.skypro.homework.entity.UserEntity;

import static ru.skypro.homework.constants.URL.USER;
import static ru.skypro.homework.utils.Examples.createRegisterDTO;
import static ru.skypro.homework.utils.Examples.createUserEntity;

public class UserMapperUtils {

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
        RegisterDTO dto = createRegisterDTO();
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