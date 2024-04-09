package ru.skypro.homework.dto.ad;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class AdsDTO {

    @Schema(description = "общее количество объявлений")
    private Integer count;

    private List<AdDTO> results;
}