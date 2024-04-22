package ru.skypro.homework.dto.ad;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
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