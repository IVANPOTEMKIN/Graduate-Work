package ru.skypro.homework.utils.mapper;

import ru.skypro.homework.dto.ad.AdDTO;
import ru.skypro.homework.dto.ad.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ad.ExtendedAdDTO;
import ru.skypro.homework.entity.AdEntity;

import static ru.skypro.homework.constants.URL.AD;
import static ru.skypro.homework.utils.Examples.createAdEntity;
import static ru.skypro.homework.utils.Examples.createCreateOrUpdateAdDTO;

public class AdMapperUtils {

    public static AdDTO getAdDTO_From_AdEntity() {
        AdEntity entity = createAdEntity();
        AdDTO dto = new AdDTO();

        dto.setAuthor(entity.getAuthor().getId());
        dto.setImage(AD.getUrl() + entity.getImage().getId());
        dto.setPk(entity.getId());
        dto.setPrice(entity.getPrice());
        dto.setTitle(entity.getTitle());

        return dto;
    }

    public static ExtendedAdDTO getExtendedAdDTO_From_AdEntity() {
        AdEntity entity = createAdEntity();
        ExtendedAdDTO dto = new ExtendedAdDTO();

        dto.setPk(entity.getId());
        dto.setAuthorFirstName(entity.getAuthor().getFirstName());
        dto.setAuthorLastName(entity.getAuthor().getLastName());
        dto.setDescription(entity.getDescription());
        dto.setEmail(entity.getAuthor().getUsername());
        dto.setImage(AD.getUrl() + entity.getImage().getId());
        dto.setPhone(entity.getAuthor().getPhoneNumber());
        dto.setPrice(entity.getPrice());
        dto.setTitle(entity.getTitle());

        return dto;
    }

    public static AdEntity getAdEntity_From_CreateOrUpdateAdDTO() {
        CreateOrUpdateAdDTO dto = createCreateOrUpdateAdDTO();
        AdEntity entity = new AdEntity();

        entity.setTitle(dto.getTitle());
        entity.setPrice(dto.getPrice());
        entity.setDescription(dto.getDescription());

        return entity;
    }
}