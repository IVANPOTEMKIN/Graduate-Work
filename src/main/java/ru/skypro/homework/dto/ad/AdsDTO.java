package ru.skypro.homework.dto.ad;

import lombok.Data;

import java.util.List;

@Data
public class AdsDTO {

    private int count; // общее количество объявлений
    private List<AdDTO> results;
}