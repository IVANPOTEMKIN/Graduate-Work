package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class RegisterDTO {

    @Size(min = 4, max = 32)
    private String username; // логин
    @Size(min = 8, max = 16)
    private String password; // пароль
    @Size(min = 2, max = 16)
    private String firstName; // имя пользователя
    @Size(min = 2, max = 16)
    private String lastName; // фамилия пользователя
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone; // номер телефона пользователя
    private Role role; // роль пользователя
}