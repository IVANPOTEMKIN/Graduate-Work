package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.auth.NewPasswordDTO;
import ru.skypro.homework.dto.user.UpdateUserDTO;
import ru.skypro.homework.dto.user.UserDTO;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.password.ReusePasswordException;
import ru.skypro.homework.exception.password.WrongPasswordException;
import ru.skypro.homework.exception.user.UserNotFoundException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

@Service
@Validated
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder encoder;
    private final UserRepository repository;
    private final UserMapper mapper;
    private final ImageService service;

    @Override
    public ResponseEntity<?> updatePassword(NewPasswordDTO dto,
                                            Authentication auth) {

        UserEntity user = getUser(auth.getName());
        String password = checkPasswords(dto, user);
        user.setPassword(encoder.encode(password));
        repository.save(user);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<UserDTO> getInfoAboutUser(Authentication auth) {
        UserEntity user = getUser(auth.getName());
        return ResponseEntity.ok(mapper.toUserDTO(user));
    }

    @Override
    public ResponseEntity<UpdateUserDTO> updateInfoAboutUser(UpdateUserDTO dto,
                                                             Authentication auth) {

        UserEntity user = getUser(auth.getName());

        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPhoneNumber(dto.getPhone());

        repository.save(user);
        return ResponseEntity.ok(mapper.toUpdateUserDTO(user));
    }

    @Override
    public ResponseEntity<?> updateAvatarOfUser(MultipartFile file,
                                                Authentication auth) {

        UserEntity user = getUser(auth.getName());
        ImageEntity avatar = service.saveImage(file);
        user.setAvatar(avatar);
        repository.save(user);
        return ResponseEntity.ok().build();
    }

    @Override
    public UserEntity getUser(String username) {
        return repository.findUserEntityByUsername(username)
                .orElseThrow(UserNotFoundException::new);
    }

    private String checkPasswords(NewPasswordDTO dto,
                                  UserEntity user) {

        String currentPassword = dto.getCurrentPassword();
        String newPassword = dto.getNewPassword();

        if (!encoder.matches(currentPassword, user.getPassword())) {
            throw new WrongPasswordException();

        } else if (encoder.matches(newPassword, user.getPassword())) {
            throw new ReusePasswordException();
        }

        return newPassword;
    }
}