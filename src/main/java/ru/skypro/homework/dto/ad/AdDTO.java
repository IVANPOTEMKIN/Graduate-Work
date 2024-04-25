package ru.skypro.homework.dto.ad;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <code> <b> author </b> </code> <i> ID автора объявления </i> <br>
 * <code> <b> image </b> </code> <i> Ссылка на картинку объявления </i> <br>
 * <code> <b> pk </b> </code> <i> ID объявления </i> <br>
 * <code> <b> price </b> </code> <i> Цена объявления </i> <br>
 * <code> <b> title </b> </code> <i> Заголовок объявления </i> <br>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdDTO {

    @Schema(description = "ID автора объявления")
    private Integer author;

    @Schema(description = "Ссылка на картинку объявления")
    private String image;

    @Schema(description = "ID объявления")
    private Integer pk;

    @Schema(description = "Цена объявления")
    private Integer price;

    @Schema(description = "Заголовок объявления")
    private String title;
}