package ru.skypro.homework.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.homework.dto.Role;

/**
 * <code> <b> id </b> </code> <i> ID пользователя </i> <br
 * <code> <b> email </b> </code> <i> Логин пользователя </i> <br
 * <code> <b> firstName </b> </code> <i> Имя пользователя </i> <br
 * <code> <b> lastName </b> </code> <i> Фамилия пользователя </i> <br
 * <code> <b> phone </b> </code> <i> Телефон пользователя </i> <br
 * <code> <b> role </b> </code> <i> Роль пользователя </i> <br
 * <code> <b> image </b> </code> <i> Ссылка на аватар пользователя </i> <br
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @Schema(description = "ID пользователя")
    private Integer id;

    @Schema(description = "Логин пользователя")
    private String email;

    @Schema(description = "Имя пользователя")
    private String firstName;

    @Schema(description = "Фамилия пользователя")
    private String lastName;

    @Schema(description = "Телефон пользователя")
    private String phone;

    @Schema(description = "Роль пользователя")
    private Role role;

    @Schema(description = "Ссылка на аватар пользователя")
    private String image;
}