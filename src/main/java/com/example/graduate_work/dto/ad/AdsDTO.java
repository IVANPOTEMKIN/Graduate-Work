package com.example.graduate_work.dto.ad;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdsDTO {

    @Schema(description = "Общее количество объявлений")
    private Integer count;

    private List<AdDTO> results;
}