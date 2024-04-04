package ru.skypro.homework.dto.ad;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
public class CreateOrUpdateAdDTO {

    @Size(min = 4, max = 32)
    private String title; // заголовок объявления
    @Min(value = 0)
    @Max(value = 10_000_000)
    private int price; // цена объявления
    @Size(min = 8, max = 64)
    private String description; // описание объявления
}