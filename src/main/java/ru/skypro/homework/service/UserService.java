package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.auth.NewPasswordDTO;
import ru.skypro.homework.dto.user.UpdateUserDTO;
import ru.skypro.homework.dto.user.UserDTO;
import ru.skypro.homework.entity.UserEntity;

import javax.validation.Valid;

public interface UserService {

    /**
     * Обновление пароля
     *
     * @param dto {@link NewPasswordDTO}
     * @return {@link Boolean} <i> Результат выполнения метода </i>
     */
    boolean updatePassword(@Valid NewPasswordDTO dto);

    /**
     * Получение информации об авторизованном пользователе
     *
     * @return {@link UserDTO}
     */
    UserDTO getInfoAboutUser();

    /**
     * Обновление информации об авторизованном пользователе
     *
     * @param dto {@link UpdateUserDTO}
     * @return {@link UpdateUserDTO}
     */
    UpdateUserDTO updateInfoAboutUser(@Valid UpdateUserDTO dto);

    /**
     * Обновление аватара авторизованного пользователя
     *
     * @param file {@link MultipartFile}
     * @return {@link Boolean} <i> Результат выполнения метода </i>
     */
    boolean updateAvatarOfUser(MultipartFile file);

    /**
     * Получение текущего пользователя
     *
     * @return {@link UserEntity}
     */
    UserEntity getUser();
}