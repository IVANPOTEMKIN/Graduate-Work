package ru.skypro.homework.utils.mapper.user;

import ru.skypro.homework.dto.auth.LoginDTO;
import ru.skypro.homework.dto.auth.NewPasswordDTO;
import ru.skypro.homework.dto.auth.RegisterDTO;
import ru.skypro.homework.dto.user.UpdateUserDTO;
import ru.skypro.homework.dto.user.UserDTO;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;

import static ru.skypro.homework.dto.Role.USER;
import static ru.skypro.homework.utils.mapper.Constants.*;

public class MappingToUserEntity {

    public static UserDTO createUserDTO() {
        UserDTO dto = new UserDTO();

        dto.setId(ID);
        dto.setEmail(USERNAME);
        dto.setFirstName(FIRST_NAME);
        dto.setLastName(LAST_NAME);
        dto.setPhone(PHONE_NUMBER);
        dto.setRole(USER);
        dto.setImage(FILE_PATH);

        return dto;
    }

    public static UpdateUserDTO createUpdateUserDTO() {
        UpdateUserDTO dto = new UpdateUserDTO();

        dto.setFirstName(FIRST_NAME);
        dto.setLastName(LAST_NAME);
        dto.setPhone(PHONE_NUMBER);

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

        dto.setNewPassword(PASSWORD);

        return dto;
    }

    public static RegisterDTO createRegisterDTO() {
        RegisterDTO dto = new RegisterDTO();

        dto.setUsername(USERNAME);
        dto.setPassword(PASSWORD);
        dto.setFirstName(FIRST_NAME);
        dto.setLastName(LAST_NAME);
        dto.setPhone(PHONE_NUMBER);
        dto.setRole(USER);

        return dto;
    }

    public static UserEntity createUserEntityFromUserDTO() {
        UserDTO dto = createUserDTO();
        UserEntity entity = new UserEntity();

        entity.setAvatar(new ImageEntity());

        entity.setId(dto.getId());
        entity.setUsername(dto.getEmail());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPhoneNumber(dto.getPhone());
        entity.setRole(dto.getRole());
        entity.getAvatar().setFilePath(dto.getImage());

        return entity;
    }

    public static UserEntity createUserEntityFromUpdateUserDTO() {
        UpdateUserDTO dto = createUpdateUserDTO();
        UserEntity entity = new UserEntity();

        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPhoneNumber(dto.getPhone());

        return entity;
    }

    public static UserEntity createUserEntityFromLoginDTO() {
        LoginDTO dto = createLoginDTO();
        UserEntity entity = new UserEntity();

        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());

        return entity;
    }

    public static UserEntity createUserEntityFromNewPasswordDTO() {
        NewPasswordDTO dto = createNewPasswordDTO();
        UserEntity entity = new UserEntity();

        entity.setPassword(dto.getNewPassword());

        return entity;
    }

    public static UserEntity createUserEntityFromRegisterDTO() {
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