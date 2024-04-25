package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.auth.RegisterDTO;
import ru.skypro.homework.dto.user.UpdateUserDTO;
import ru.skypro.homework.dto.user.UserDTO;
import ru.skypro.homework.entity.UserEntity;

public interface UserMapper {

    /**
     * {@link UserEntity} -> {@link UserDTO}
     */
    UserDTO toUserDTO(UserEntity entity);

    /**
     * {@link UserEntity} -> {@link UpdateUserDTO}
     */
    UpdateUserDTO toUpdateUserDTO(UserEntity entity);

    /**
     * {@link RegisterDTO} -> {@link UserEntity}
     */
    UserEntity toUserEntity(RegisterDTO dto);
}