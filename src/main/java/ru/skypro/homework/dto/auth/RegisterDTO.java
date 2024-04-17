package ru.skypro.homework.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ru.skypro.homework.dto.Role;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class RegisterDTO {

    @Schema(description = "Логин")
    @Size(min = 4, max = 32)
    private String username;

    @Schema(description = "Пароль")
    @Size(min = 8, max = 16)
    private String password;

    @Schema(description = "Имя пользователя")
    @Size(min = 2, max = 16)
    private String firstName;

    @Schema(description = "Фамилия пользователя")
    @Size(min = 2, max = 16)
    private String lastName;

    @Schema(description = "Телефон пользователя")
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;

    @Schema(description = "Роль пользователя")
    private Role role;
}