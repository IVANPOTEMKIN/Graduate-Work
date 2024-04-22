package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.auth.NewPasswordDTO;
import ru.skypro.homework.dto.user.UpdateUserDTO;
import ru.skypro.homework.dto.user.UserDTO;
import ru.skypro.homework.entity.UserEntity;

import javax.validation.Valid;

public interface UserService {

    boolean updatePassword(@Valid NewPasswordDTO dto);

    UserDTO getInfoAboutUser();

    UpdateUserDTO updateInfoAboutUser(@Valid UpdateUserDTO dto);

    boolean updateAvatarOfUser(MultipartFile file);

    UserEntity getUser();
}