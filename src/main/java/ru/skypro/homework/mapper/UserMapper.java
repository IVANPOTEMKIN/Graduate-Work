package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.auth.RegisterDTO;
import ru.skypro.homework.dto.user.UpdateUserDTO;
import ru.skypro.homework.dto.user.UserDTO;
import ru.skypro.homework.entity.UserEntity;

public interface UserMapper {

    UserDTO toUserDTO(UserEntity entity);

    UpdateUserDTO toUpdateUserDTO(UserEntity entity);

    UserEntity toUserEntity(RegisterDTO dto);
}