package com.example.graduate_work.utils.mapper;

import com.example.graduate_work.dto.auth.RegisterDTO;
import com.example.graduate_work.dto.user.UpdateUserDTO;
import com.example.graduate_work.dto.user.UserDTO;
import com.example.graduate_work.entity.UserEntity;

import static com.example.graduate_work.utils.Examples.createRegisterDTO;
import static com.example.graduate_work.utils.Examples.createUserEntity;
import static com.example.graduate_work.utils.URL.USER;

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