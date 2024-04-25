package ru.skypro.homework.mapper.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.auth.RegisterDTO;
import ru.skypro.homework.dto.user.UpdateUserDTO;
import ru.skypro.homework.dto.user.UserDTO;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.UserMapper;

import static ru.skypro.homework.constants.URL.USER;

@Service
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO toUserDTO(UserEntity entity) {
        UserDTO dto = new UserDTO();

        dto.setId(entity.getId());
        dto.setEmail(entity.getUsername());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setPhone(entity.getPhoneNumber());
        dto.setRole(entity.getRole());

        if (entity.getAvatar() != null) {
            dto.setImage(USER.getUrl() + entity.getAvatar().getId());
        }

        return dto;
    }

    @Override
    public UpdateUserDTO toUpdateUserDTO(UserEntity entity) {
        UpdateUserDTO dto = new UpdateUserDTO();

        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setPhone(entity.getPhoneNumber());

        return dto;
    }

    @Override
    public UserEntity toUserEntity(RegisterDTO dto) {
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