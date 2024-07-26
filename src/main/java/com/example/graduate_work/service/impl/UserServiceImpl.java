package com.example.graduate_work.service.impl;

import com.example.graduate_work.dto.auth.NewPasswordDTO;
import com.example.graduate_work.dto.user.UpdateUserDTO;
import com.example.graduate_work.dto.user.UserDTO;
import com.example.graduate_work.entity.ImageEntity;
import com.example.graduate_work.entity.UserEntity;
import com.example.graduate_work.exception.password.ReusePasswordException;
import com.example.graduate_work.exception.password.WrongPasswordException;
import com.example.graduate_work.exception.user.UserNotFoundException;
import com.example.graduate_work.mapper.UserMapper;
import com.example.graduate_work.repository.UserRepository;
import com.example.graduate_work.service.ImageService;
import com.example.graduate_work.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;

@Service
@Validated
@RequiredArgsConstructor
@Transactional(isolation = SERIALIZABLE)
public class UserServiceImpl implements UserService {

    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final ImageService imageService;
    private final UserMapper userMapper;

    @Override
    public boolean updatePassword(NewPasswordDTO dto) {
        UserEntity user = getUser();
        String password = checkPasswords(dto, user);
        user.setPassword(encoder.encode(password));
        userRepository.save(user);
        return true;
    }

    @Override
    public UserDTO getInfoAboutUser() {
        UserEntity user = getUser();
        return userMapper.toUserDTO(user);
    }

    @Override
    public UpdateUserDTO updateInfoAboutUser(UpdateUserDTO dto) {
        UserEntity user = getUser();

        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPhoneNumber(dto.getPhone());

        userRepository.save(user);
        return userMapper.toUpdateUserDTO(user);
    }

    @Override
    public boolean updateAvatarOfUser(MultipartFile file) {
        UserEntity user = getUser();
        ImageEntity avatar = imageService.saveImage(file);
        user.setAvatar(avatar);
        userRepository.save(user);
        return true;
    }

    @Override
    public UserEntity getUser() {
        return userRepository.findUserEntityByUsername(getCurrentUsername())
                .orElseThrow(UserNotFoundException::new);
    }

    /**
     * Выполнение проверок при смене пароля
     *
     * @param dto  {@link NewPasswordDTO}
     * @param user {@link UserEntity}
     * @return {@link String} <i> Корректный пароль </i>
     */
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

    /**
     * Получение логина текущего пользователя
     *
     * @return {@link String} <i> Логин текущего пользователя </i>
     */
    private String getCurrentUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}