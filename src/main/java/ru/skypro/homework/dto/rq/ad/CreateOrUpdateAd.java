package ru.skypro.homework.dto.rq.ad;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateOrUpdateAd {
    /**
     * Поля относящиеся к сущности AdEntity
     */
    private String title; // заголовок объявления
    private Integer price; //  цена объявления
    private String description; // описание объявления


}
