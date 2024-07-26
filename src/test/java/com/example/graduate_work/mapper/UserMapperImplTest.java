package com.example.graduate_work.mapper;

import com.example.graduate_work.dto.user.UpdateUserDTO;
import com.example.graduate_work.dto.user.UserDTO;
import com.example.graduate_work.entity.UserEntity;
import com.example.graduate_work.mapper.impl.UserMapperImpl;
import org.junit.jupiter.api.Test;

import static com.example.graduate_work.utils.Examples.createRegisterDTO;
import static com.example.graduate_work.utils.Examples.createUserEntity;
import static com.example.graduate_work.utils.mapper.UserMapperUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
        UserEntity actual = mapper.toUserEntity(createRegisterDTO());

        assertNotNull(actual);
        assertEquals(expected, actual);
    }
}