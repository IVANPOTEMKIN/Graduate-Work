package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.auth.NewPasswordDTO;
import ru.skypro.homework.dto.user.UpdateUserDTO;
import ru.skypro.homework.dto.user.UserDTO;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import java.io.IOException;

import static ru.skypro.homework.mapper.UserMapper.INSTANCE;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ImageService imageService;

    @Override
    public void updatePassword(NewPasswordDTO dto,
                               Authentication auth) {

        UserEntity userEntity = getUser(auth.getName());
        userEntity.setPassword(dto.getNewPassword());
        userRepository.save(userEntity);
    }

    @Override
    public UserDTO getInfoAboutUser(Authentication auth) {
        UserEntity userEntity = getUser(auth.getName());
        return INSTANCE.toUserDTO(userEntity);
    }

    @Override
    public UpdateUserDTO updateInfoAboutUser(UpdateUserDTO dto,
                                             Authentication auth) {

        UserEntity userEntity = getUser(auth.getName());

        userEntity.setFirstName(dto.getFirstName());
        userEntity.setLastName(dto.getLastName());
        userEntity.setPhoneNumber(dto.getPhone());

        userRepository.save(userEntity);
        return INSTANCE.toUpdateUserDTO(userEntity);
    }

    @Override
    public void updateAvatarOfUser(MultipartFile image,
                                   Authentication auth) {

        UserEntity userEntity = getUser(auth.getName());
        ImageEntity avatar = getAvatar(image);

        userEntity.setAvatar(avatar);
        userRepository.save(userEntity);
    }

    private ImageEntity getAvatar(MultipartFile image) {
        ImageEntity avatar;

        try {
            avatar = imageService.saveFile(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return avatar;
    }

    @Override
    public UserEntity getUser(String username) {
        return userRepository.findUserEntityByUsername(username).orElseThrow();
    }
}