package ru.skypro.homework.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class LoginDTO {

    @Schema(description = "Логин")
    @Size(min = 4, max = 32,
            message = "ЛОГИН ДОЛЖЕН БЫТЬ В ДИАПАЗОНЕ ОТ 4 ДО 32 СИМВОЛОВ!")
    private String username;

    @Schema(description = "Пароль")
    @Size(min = 8, max = 16,
            message = "ПАРОЛЬ ДОЛЖЕН БЫТЬ В ДИАПАЗОНЕ ОТ 8 ДО 16 СИМВОЛОВ!")
    private String password;
}