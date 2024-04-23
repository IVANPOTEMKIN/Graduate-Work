package ru.skypro.homework.dto.ad;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * <code> <b> title </b></code> <i> Заголовок объявления </i> <br>
 * <code> <b> price </b></code> <i> Цена объявления </i> <br>
 * <code> <b> description </b></code> <i> Описание объявления </i> <br>
 */
@Data
public class CreateOrUpdateAdDTO {

    @Schema(description = "Заголовок объявления")
    @Size(min = 4, max = 32,
            message = "ЗАГОЛОВОК ОБЪЯВЛЕНИЯ ДОЛЖЕН БЫТЬ В ДИАПАЗОНЕ ОТ 4 ДО 32 СИМВОЛОВ!")
    private String title;

    @Schema(description = "Цена объявления")
    @Min(value = 0, message = "ЦЕНА ОБЪЯВЛЕНИЯ НЕ МОЖЕТ БЫТЬ МЕНЬШЕ 0!")
    @Max(value = 10_000_000, message = "ЦЕНА ОБЪЯВЛЕНИЯ НЕ МОЖЕТ БЫТЬ БОЛЬШЕ 10 000 000!")
    private Integer price;

    @Schema(description = "Описание объявления")
    @Size(min = 8, max = 64,
            message = "ОПИСАНИЕ ОБЪЯВЛЕНИЯ ДОЛЖНО БЫТЬ В ДИАПАЗОНЕ ОТ 8 ДО 64 СИМВОЛОВ!")
    private String description;
}