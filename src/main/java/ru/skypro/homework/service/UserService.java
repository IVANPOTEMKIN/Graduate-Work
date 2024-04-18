package ru.skypro.homework.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.auth.NewPasswordDTO;
import ru.skypro.homework.dto.user.UpdateUserDTO;
import ru.skypro.homework.dto.user.UserDTO;
import ru.skypro.homework.entity.UserEntity;

import javax.validation.Valid;

public interface UserService {

    ResponseEntity<?> updatePassword(@Valid NewPasswordDTO dto,
                                     Authentication auth);

    ResponseEntity<UserDTO> getInfoAboutUser(Authentication auth);

    ResponseEntity<UpdateUserDTO> updateInfoAboutUser(@Valid UpdateUserDTO dto,
                                                      Authentication auth);

    ResponseEntity<?> updateAvatarOfUser(MultipartFile file,
                                         Authentication auth);

    UserEntity getUser(String username);
}