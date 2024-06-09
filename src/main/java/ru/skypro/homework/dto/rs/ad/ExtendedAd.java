package ru.skypro.homework.dto.rs.ad;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExtendedAd {
    /**
     * Поля относящиеся к сущности AdEntity
     */
    private Integer pk;
    private String description;
    private String image;
    private Integer price;
    private String title;
    /**
     * Поля относящиеся к сущности UserEntity
     */
    private String authorFirstName;
    private String authorLastName;
    private String username;
    private String phone;
}
