package com.example.graduate_work.mapper.impl;

import com.example.graduate_work.dto.ad.AdDTO;
import com.example.graduate_work.dto.ad.CreateOrUpdateAdDTO;
import com.example.graduate_work.dto.ad.ExtendedAdDTO;
import com.example.graduate_work.entity.AdEntity;
import com.example.graduate_work.mapper.AdMapper;
import org.springframework.stereotype.Service;

import static com.example.graduate_work.utils.URL.AD;

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