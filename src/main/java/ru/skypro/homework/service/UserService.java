package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.auth.NewPasswordDTO;
import ru.skypro.homework.dto.user.UpdateUserDTO;
import ru.skypro.homework.dto.user.UserDTO;
import ru.skypro.homework.entity.UserEntity;

import javax.validation.Valid;

public interface UserService {

    void updatePassword(@Valid NewPasswordDTO dto,
                        Authentication auth);

    UserDTO getInfoAboutUser(Authentication auth);

    UpdateUserDTO updateInfoAboutUser(@Valid UpdateUserDTO dto,
                                      Authentication auth);

    void updateAvatarOfUser(MultipartFile file,
                            Authentication auth);

    UserEntity getUser(String username);
}