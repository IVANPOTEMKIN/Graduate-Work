package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.auth.NewPasswordDTO;
import ru.skypro.homework.dto.user.UpdateUserDTO;
import ru.skypro.homework.dto.user.UserDTO;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final ImageService service;
    private final UserMapper mapper;

    @Override
    public ResponseEntity<?> updatePassword(NewPasswordDTO dto,
                                            Authentication auth) {

        UserEntity user = getUser(auth.getName());
        user.setPassword(dto.getNewPassword());
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
}