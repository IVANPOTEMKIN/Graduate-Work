package com.example.graduate_work.mapper;

import com.example.graduate_work.dto.auth.RegisterDTO;
import com.example.graduate_work.dto.user.UpdateUserDTO;
import com.example.graduate_work.dto.user.UserDTO;
import com.example.graduate_work.entity.UserEntity;

public interface UserMapper {

    UserDTO toUserDTO(UserEntity entity);

    UpdateUserDTO toUpdateUserDTO(UserEntity entity);

    UserEntity toUserEntity(RegisterDTO dto);
}