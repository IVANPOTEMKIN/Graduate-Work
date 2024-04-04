package ru.skypro.homework.dto.user;

import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UpdateUserDTO {

    @Size(min = 3, max = 10)
    private String firstName; // имя пользователя
    @Size(min = 3, max = 10)
    private String lastName; // фамилия пользователя
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone; // номер телефона пользователя
}