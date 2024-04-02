package ru.skypro.homework.dto.user;

import lombok.Data;
import ru.skypro.homework.dto.Role;

@Data
public class UserDTO {

    private int id; // id пользователя
    private String username; // логин пользователя
    private String firstName; // имя пользователя
    private String lastName; // фамилия пользователя
    private String phone; // номер телефона пользователя
    private Role role; // роль пользователя
    private String image; // ссылка на аватар пользователя
}