package ru.skypro.homework.dto.ad;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <code> <b> pk </b> </code> <i> ID объявления </i> <br>
 * <code> <b> authorFirstName </b> </code> <i> Имя автора объявления </i> <br>
 * <code> <b> authorLastName </b> </code> <i> Фамилия автора объявления </i> <br>
 * <code> <b> description </b> </code> <i> Описание объявления </i> <br>
 * <code> <b> email </b> </code> <i> Логин автора объявления </i> <br>
 * <code> <b> image </b> </code> <i> Ссылка на картинку объявления </i> <br>
 * <code> <b> phone </b> </code> <i> Телефон автора объявления </i> <br>
 * <code> <b> price </b> </code> <i> Цена объявления </i> <br>
 * <code> <b> title </b> </code> <i> Заголовок объявления </i> <br>
 */
@Data
public class ExtendedAdDTO {

    @Schema(description = "ID объявления")
    private Integer pk;

    @Schema(description = "Имя автора объявления")
    private String authorFirstName;

    @Schema(description = "Фамилия автора объявления")
    private String authorLastName;

    @Schema(description = "Описание объявления")
    private String description;

    @Schema(description = "Логин автора объявления")
    private String email;

    @Schema(description = "Ссылка на картинку объявления")
    private String image;

    @Schema(description = "Телефон автора объявления")
    private String phone;

    @Schema(description = "Цена объявления")
    private Integer price;

    @Schema(description = "Заголовок объявления")
    private String title;
}