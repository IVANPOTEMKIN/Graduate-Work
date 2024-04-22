package ru.skypro.homework.dto.ad;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AdsDTO {

    @Schema(description = "Общее количество объявлений")
    private Integer count;

    private List<AdDTO> results;
}