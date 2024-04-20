package ru.skypro.homework.mapper;

import org.junit.jupiter.api.Test;
import ru.skypro.homework.dto.user.UpdateUserDTO;
import ru.skypro.homework.dto.user.UserDTO;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.impl.UserMapperImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static ru.skypro.homework.utils.mapper.UserMapperUtils.*;

class UserMapperImplTest {

    private final UserMapper mapper = new UserMapperImpl();

    @Test
    void toUserDTO() {
        UserDTO expected = getUserDTO_From_UserEntity();
        UserDTO actual = mapper.toUserDTO(createUserEntity());

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    void toUpdateUserDTO() {
        UpdateUserDTO expected = getUpdateUserDTO_From_UserEntity();
        UpdateUserDTO actual = mapper.toUpdateUserDTO(createUserEntity());

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    void toUserEntity() {
        UserEntity expected = getUserEntity_From_RegisterDTO();
        UserEntity actual = mapper.toUserEntity(createDTO());

        assertNotNull(actual);
        assertEquals(expected, actual);
    }
}