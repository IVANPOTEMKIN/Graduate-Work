package ru.skypro.homework.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class LoginDTO {

    @Schema(description = "пароль",
            minLength = 8,
            maxLength = 16)
    private String password;

    @Schema(description = "логин",
            minLength = 4,
            maxLength = 32)
    private String username;
}