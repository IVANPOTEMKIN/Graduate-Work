package ru.skypro.homework.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ru.skypro.homework.dto.Role;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class RegisterDTO {

    @Schema(description = "Логин")
    @Size(min = 4, max = 32,
            message = "ЛОГИН ДОЛЖЕН БЫТЬ В ДИАПАЗОНЕ ОТ 4 ДО 32 СИМВОЛОВ!")
    private String username;

    @Schema(description = "Пароль")
    @Size(min = 8, max = 16,
            message = "ПАРОЛЬ ДОЛЖЕН БЫТЬ В ДИАПАЗОНЕ ОТ 8 ДО 16 СИМВОЛОВ!")
    private String password;

    @Schema(description = "Имя пользователя")
    @Size(min = 2, max = 16,
            message = "ИМЯ ПОЛЬЗОВАТЕЛЯ ДОЛЖНО БЫТЬ В ДИАПАЗОНЕ ОТ 2 ДО 16 СИМВОЛОВ!")
    private String firstName;

    @Schema(description = "Фамилия пользователя")
    @Size(min = 2, max = 16,
            message = "ФАМИЛИЯ ПОЛЬЗОВАТЕЛЯ ДОЛЖНА БЫТЬ В ДИАПАЗОНЕ ОТ 2 ДО 16 СИМВОЛОВ!")
    private String lastName;

    @Schema(description = "Телефон пользователя")
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}",
            message = "ТЕЛЕФОН ПОЛЬЗОВАТЕЛЯ ДОЛЖЕН БЫТЬ В СООТВЕТСТВИИ С ШАБЛОНОМ +7 (777) 777-77-77!")
    private String phone;

    @Schema(description = "Роль пользователя")
    private Role role;
}