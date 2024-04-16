package ru.skypro.homework.mapper.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.ad.AdDTO;
import ru.skypro.homework.dto.ad.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ad.ExtendedAdDTO;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.mapper.AdMapper;

import static ru.skypro.homework.constants.URL.AD;

@Service
public class AdMapperImpl implements AdMapper {

    @Override
    public AdDTO toAdDTO(AdEntity entity) {
        AdDTO dto = new AdDTO();

        dto.setAuthor(entity.getAuthor().getId());
        dto.setImage(AD.getUrl() + entity.getImage().getId());
        dto.setPk(entity.getId());
        dto.setPrice(entity.getPrice());
        dto.setTitle(entity.getTitle());

        return dto;
    }

    @Override
    public ExtendedAdDTO toExtendedAdDTO(AdEntity entity) {
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

    @Override
    public AdEntity toAdEntity(CreateOrUpdateAdDTO dto) {
        AdEntity entity = new AdEntity();

        entity.setTitle(dto.getTitle());
        entity.setPrice(dto.getPrice());
        entity.setDescription(dto.getDescription());

        return entity;
    }
}