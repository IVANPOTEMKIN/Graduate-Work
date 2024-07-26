package com.example.graduate_work.utils.mapper;

import com.example.graduate_work.dto.ad.AdDTO;
import com.example.graduate_work.dto.ad.CreateOrUpdateAdDTO;
import com.example.graduate_work.dto.ad.ExtendedAdDTO;
import com.example.graduate_work.entity.AdEntity;

import static com.example.graduate_work.utils.Examples.createAdEntity;
import static com.example.graduate_work.utils.Examples.createCreateOrUpdateAdDTO;
import static com.example.graduate_work.utils.URL.AD;

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