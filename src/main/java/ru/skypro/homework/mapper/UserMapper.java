package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.auth.LoginDTO;
import ru.skypro.homework.dto.auth.NewPasswordDTO;
import ru.skypro.homework.dto.auth.RegisterDTO;
import ru.skypro.homework.dto.user.UpdateUserDTO;
import ru.skypro.homework.dto.user.UserDTO;
import ru.skypro.homework.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    /*
    Entity -> DTO
     */

    @Mapping(target = "email", source = "entity.username")
    @Mapping(target = "phone", source = "entity.phoneNumber")
    @Mapping(target = "image", source = "entity.avatar.filePath")
    UserDTO toUserDTO(UserEntity entity);

    @Mapping(target = "phone", source = "entity.phoneNumber")
    UpdateUserDTO toUpdateUserDTO(UserEntity entity);

    LoginDTO toLoginDTO(UserEntity entity);

    @Mapping(target = "newPassword", source = "entity.password")
    NewPasswordDTO toNewPasswordDTO(UserEntity entity);

    @Mapping(target = "phone", source = "entity.phoneNumber")
    RegisterDTO toRegisterDTO(UserEntity entity);

    /*
    DTO -> Entity
     */

    @Mapping(target = "username", source = "dto.email")
    @Mapping(target = "phoneNumber", source = "dto.phone")
    @Mapping(target = "avatar.filePath", source = "dto.image")
    UserEntity toUserEntity(UserDTO dto);

    @Mapping(target = "phoneNumber", source = "dto.phone")
    UserEntity toUserEntity(UpdateUserDTO dto);

    UserEntity toUserEntity(LoginDTO dto);

    @Mapping(target = "password", source = "dto.newPassword")
    UserEntity toUserEntity(NewPasswordDTO dto);

    @Mapping(target = "phoneNumber", source = "dto.phone")
    UserEntity toUserEntity(RegisterDTO dto);
}