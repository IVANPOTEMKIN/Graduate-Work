package ru.skypro.homework.dto.auth;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class NewPasswordDTO {

    @Size(min = 8, max = 16)
    private String currentPassword; // существующий пароль
    @Size(min = 8, max = 16)
    private String newPassword; // новый пароль
}