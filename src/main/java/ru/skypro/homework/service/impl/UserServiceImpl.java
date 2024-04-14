package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.auth.NewPasswordDTO;
import ru.skypro.homework.dto.user.UpdateUserDTO;
import ru.skypro.homework.dto.user.UserDTO;
import ru.skypro.homework.entity.AvatarEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AvatarService;
import ru.skypro.homework.service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.skypro.homework.mapper.UserMapper.INSTANCE;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AvatarService avatarService;

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
    public ResponseEntity<byte[]> updateAvatarOfUser(MultipartFile file,
                                                     HttpServletResponse response,
                                                     Authentication auth) {

        UserEntity userEntity = getUser(auth.getName());
        AvatarEntity avatar = getAvatar(file);

        userEntity.setAvatar(avatar);
        userRepository.save(userEntity);
        return avatarService.downloadFromDB(avatar.getFilePath());
    }

    private AvatarEntity getAvatar(MultipartFile file) {
        AvatarEntity avatar;

        try {
            avatar = avatarService.saveAvatar(file);
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