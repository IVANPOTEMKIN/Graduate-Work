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

    private final UserRepository repository;
    private final ImageService service;

    @Override
    public void updatePassword(NewPasswordDTO dto,
                               Authentication auth) {

        UserEntity entity = getUser(auth.getName());
        entity.setPassword(dto.getNewPassword());
        repository.save(entity);
    }

    @Override
    public UserDTO getInfoAboutUser(Authentication auth) {
        UserEntity entity = getUser(auth.getName());
        return INSTANCE.toUserDTO(entity);
    }

    @Override
    public UpdateUserDTO updateInfoAboutUser(UpdateUserDTO dto,
                                             Authentication auth) {

        UserEntity entity = getUser(auth.getName());

        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPhoneNumber(dto.getPhone());

        repository.save(entity);
        return INSTANCE.toUpdateUserDTO(entity);
    }

    @Override
    public void updateAvatarOfUser(MultipartFile image,
                                   Authentication auth) {

        UserEntity user = getUser(auth.getName());
        ImageEntity avatar;

        try {
            avatar = service.saveFile(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        user.setAvatar(avatar);
        repository.save(user);
    }

    private UserEntity getUser(String username) {
        return repository.findUserEntityByUsername(username).orElseThrow();
    }
}