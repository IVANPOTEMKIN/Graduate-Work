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

public class MappingFromUserEntity {

    public static UserEntity createUserEntity() {
        UserEntity entity = new UserEntity();

        entity.setAvatar(new ImageEntity());

        entity.setId(ID);
        entity.setUsername(USERNAME);
        entity.setPassword(PASSWORD);
        entity.setFirstName(FIRST_NAME);
        entity.setLastName(LAST_NAME);
        entity.setPhoneNumber(PHONE_NUMBER);
        entity.setRole(USER);
        entity.getAvatar().setFilePath(FILE_PATH);

        return entity;
    }

    public static UserDTO createUserDTOFromUserEntity() {
        UserEntity entity = createUserEntity();
        UserDTO dto = new UserDTO();

        dto.setId(entity.getId());
        dto.setEmail(entity.getUsername());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setPhone(entity.getPhoneNumber());
        dto.setRole(entity.getRole());
        dto.setImage(entity.getAvatar().getFilePath());

        return dto;
    }

    public static UpdateUserDTO createUpdateUserDTOFromUserEntity() {
        UserEntity entity = createUserEntity();
        UpdateUserDTO dto = new UpdateUserDTO();

        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setPhone(entity.getPhoneNumber());

        return dto;
    }

    public static LoginDTO createLoginDTOFromUserEntity() {
        UserEntity entity = createUserEntity();
        LoginDTO dto = new LoginDTO();

        dto.setUsername(entity.getUsername());
        dto.setPassword(entity.getPassword());

        return dto;
    }

    public static NewPasswordDTO createNewPasswordDTOFromUserEntity() {
        UserEntity entity = createUserEntity();
        NewPasswordDTO dto = new NewPasswordDTO();

        dto.setNewPassword(entity.getPassword());

        return dto;
    }

    public static RegisterDTO createRegisterDTOFromUserEntity() {
        UserEntity entity = createUserEntity();
        RegisterDTO dto = new RegisterDTO();

        dto.setUsername(entity.getUsername());
        dto.setPassword(entity.getPassword());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setPhone(entity.getPhoneNumber());
        dto.setRole(entity.getRole());

        return dto;
    }
}