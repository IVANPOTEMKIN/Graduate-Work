package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.ad.AdDTO;
import ru.skypro.homework.dto.ad.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ad.ExtendedAdDTO;
import ru.skypro.homework.entity.AdEntity;

public interface AdMapper {

    /**
     * {@link AdEntity} -> {@link AdDTO}
     */
    AdDTO toAdDTO(AdEntity entity);

    /**
     * {@link AdEntity} -> {@link ExtendedAdDTO}
     */
    ExtendedAdDTO toExtendedAdDTO(AdEntity entity);

    /**
     * {@link CreateOrUpdateAdDTO} -> {@link AdEntity}
     */
    AdEntity toAdEntity(CreateOrUpdateAdDTO dto);
}