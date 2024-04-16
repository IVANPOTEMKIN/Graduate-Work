package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.ad.AdDTO;
import ru.skypro.homework.dto.ad.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ad.ExtendedAdDTO;
import ru.skypro.homework.entity.AdEntity;

public interface AdMapper {

    AdDTO toAdDTO(AdEntity entity);

    ExtendedAdDTO toExtendedAdDTO(AdEntity entity);

    AdEntity toAdEntity(CreateOrUpdateAdDTO dto);
}