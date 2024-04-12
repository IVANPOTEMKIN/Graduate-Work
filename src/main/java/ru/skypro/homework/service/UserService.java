package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.auth.NewPasswordDTO;
import ru.skypro.homework.dto.user.UpdateUserDTO;
import ru.skypro.homework.dto.user.UserDTO;
import ru.skypro.homework.entity.UserEntity;

public interface UserService {

    void updatePassword(NewPasswordDTO dto,
                        Authentication auth);

    UserDTO getInfoAboutUser(Authentication auth);

    UpdateUserDTO updateInfoAboutUser(UpdateUserDTO dto,
                                      Authentication auth);

    void updateAvatarOfUser(MultipartFile image,
                            Authentication auth);

    UserEntity getUser(String username);
}