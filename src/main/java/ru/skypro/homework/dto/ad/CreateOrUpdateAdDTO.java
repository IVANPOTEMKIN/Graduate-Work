package ru.skypro.homework.dto.ad;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
public class CreateOrUpdateAdDTO {

    @Schema(description = "Заголовок объявления")
    @Size(min = 4, max = 32)
    private String title;

    @Schema(description = "Цена объявления")
    @Min(0)
    @Max(10_000_000)
    private Integer price;

    @Schema(description = "Описание объявления")
    @Size(min = 8, max = 64)
    private String description;
}