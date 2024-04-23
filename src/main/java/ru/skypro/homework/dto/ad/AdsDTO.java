package ru.skypro.homework.dto.ad;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * <code> <b> count </b> </code> <i> Общее количество объявлений </i> <br>
 * <code> <b> results </b> </code> <code> List<{@link AdDTO}> </code> <br>
 */
@Data
@AllArgsConstructor
public class AdsDTO {

    @Schema(description = "Общее количество объявлений")
    private Integer count;

    private List<AdDTO> results;
}