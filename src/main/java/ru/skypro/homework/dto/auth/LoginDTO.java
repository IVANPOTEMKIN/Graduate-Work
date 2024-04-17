package ru.skypro.homework.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class LoginDTO {

    @Schema(description = "Логин")
    @Size(min = 4, max = 32)
    private String username;

    @Schema(description = "Пароль")
    @Size(min = 8, max = 16)
    private String password;
}