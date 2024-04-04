package ru.skypro.homework.dto.ad;

import lombok.Data;

@Data
public class ExtendedAdDTO {

    private int pk; // id объявления
    private String authorFirstName; // имя автора объявления
    private String authorLastName; // фамилия автора объявления
    private String description; // описание объявления
    private String username; // логин автора объявления
    private String image; // ссылка на картинку объявления
    private String phone; // номер телефона автора объявления
    private int price; // цена объявления
    private String title; // заголовок объявления
}