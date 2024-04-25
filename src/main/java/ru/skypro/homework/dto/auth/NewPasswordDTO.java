package ru.skypro.homework.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

/**
 * <code> <b> currentPassword </b> </code> <i> Текущий пароль </i> <br>
 * <code> <b> newPassword </b> </code> <i> Новый пароль </i> <br>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewPasswordDTO {

    @Schema(description = "Текущий пароль")
    @Size(min = 8, max = 16,
            message = "ТЕКУЩИЙ ПАРОЛЬ ДОЛЖЕН БЫТЬ В ДИАПАЗОНЕ ОТ 8 ДО 16 СИМВОЛОВ!")
    private String currentPassword;

    @Schema(description = "Новый пароль")
    @Size(min = 8, max = 16,
            message = "НОВЫЙ ПАРОЛЬ ДОЛЖЕН БЫТЬ В ДИАПАЗОНЕ ОТ 8 ДО 16 СИМВОЛОВ!")
    private String newPassword;
}