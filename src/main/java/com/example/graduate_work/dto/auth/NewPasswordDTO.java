package com.example.graduate_work.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

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