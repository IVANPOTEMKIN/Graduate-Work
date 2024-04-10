package ru.skypro.homework.mapper;

import org.junit.jupiter.api.Test;
import ru.skypro.homework.dto.auth.LoginDTO;
import ru.skypro.homework.dto.auth.NewPasswordDTO;
import ru.skypro.homework.dto.auth.RegisterDTO;
import ru.skypro.homework.dto.user.UpdateUserDTO;
import ru.skypro.homework.dto.user.UserDTO;
import ru.skypro.homework.entity.UserEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static ru.skypro.homework.mapper.UserMapper.INSTANCE;
import static ru.skypro.homework.utils.mapper.user.MappingFromUserEntity.*;
import static ru.skypro.homework.utils.mapper.user.MappingToUserEntity.*;

public class UserMapperTest {

    @Test
    void to_UserDTO_From_UserEntity() {
        UserEntity entity = createUserEntity();

        UserDTO expected = createUserDTOFromUserEntity();
        UserDTO actual = INSTANCE.toUserDTO(entity);

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    void to_UpdateUserDTO_From_UserEntity() {
        UserEntity entity = createUserEntity();

        UpdateUserDTO expected = createUpdateUserDTOFromUserEntity();
        UpdateUserDTO actual = INSTANCE.toUpdateUserDTO(entity);

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    void to_LoginDTO_From_UserEntity() {
        UserEntity entity = createUserEntity();

        LoginDTO expected = createLoginDTOFromUserEntity();
        LoginDTO actual = INSTANCE.toLoginDTO(entity);

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    void to_NewPasswordDTO_From_UserEntity() {
        UserEntity entity = createUserEntity();

        NewPasswordDTO expected = createNewPasswordDTOFromUserEntity();
        NewPasswordDTO actual = INSTANCE.toNewPasswordDTO(entity);

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    void to_RegisterDTO_From_UserEntity() {
        UserEntity entity = createUserEntity();

        RegisterDTO expected = createRegisterDTOFromUserEntity();
        RegisterDTO actual = INSTANCE.toRegisterDTO(entity);

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    void to_UserEntity_From_UserDTO() {
        UserDTO dto = createUserDTO();

        UserEntity expected = createUserEntityFromUserDTO();
        UserEntity actual = INSTANCE.toUserEntity(dto);

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    void to_UserEntity_From_UpdateUserDTO() {
        UpdateUserDTO dto = createUpdateUserDTO();

        UserEntity expected = createUserEntityFromUpdateUserDTO();
        UserEntity actual = INSTANCE.toUserEntity(dto);

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    void to_UserEntity_From_LoginDTO() {
        LoginDTO dto = createLoginDTO();

        UserEntity expected = createUserEntityFromLoginDTO();
        UserEntity actual = INSTANCE.toUserEntity(dto);

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    void to_UserEntity_From_NewPasswordDTO() {
        NewPasswordDTO dto = createNewPasswordDTO();

        UserEntity expected = createUserEntityFromNewPasswordDTO();
        UserEntity actual = INSTANCE.toUserEntity(dto);

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    void to_UserEntity_From_RegisterDTO() {
        RegisterDTO dto = createRegisterDTO();

        UserEntity expected = createUserEntityFromRegisterDTO();
        UserEntity actual = INSTANCE.toUserEntity(dto);

        assertNotNull(actual);
        assertEquals(expected, actual);
    }
}