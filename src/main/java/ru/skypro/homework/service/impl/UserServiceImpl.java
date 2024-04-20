package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;

@Service
@Validated
@RequiredArgsConstructor
@Transactional(isolation = SERIALIZABLE)
public class UserServiceImpl implements UserService {

    private final PasswordEncoder encoder;
    private final UserRepository repository;
    private final ImageService imageService;
    private final UserMapper mapper;

    @Override
    public void updatePassword(NewPasswordDTO dto,
                               Authentication auth) {

        UserEntity user = getUser(auth.getName());
        String password = checkPasswords(dto, user);
        user.setPassword(encoder.encode(password));
        repository.save(user);
    }

    @Override
    public UserDTO getInfoAboutUser(Authentication auth) {
        UserEntity user = getUser(auth.getName());
        return mapper.toUserDTO(user);
    }

    @Override
    public UpdateUserDTO updateInfoAboutUser(UpdateUserDTO dto,
                                             Authentication auth) {

        UserEntity user = getUser(auth.getName());

        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPhoneNumber(dto.getPhone());

        repository.save(user);
        return mapper.toUpdateUserDTO(user);
    }

    @Override
    public void updateAvatarOfUser(MultipartFile file,
                                   Authentication auth) {

        UserEntity user = getUser(auth.getName());
        ImageEntity avatar = imageService.saveImage(file);
        user.setAvatar(avatar);
        repository.save(user);
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