package com.example.graduate_work.mapper;

import com.example.graduate_work.dto.ad.AdDTO;
import com.example.graduate_work.dto.ad.CreateOrUpdateAdDTO;
import com.example.graduate_work.dto.ad.ExtendedAdDTO;
import com.example.graduate_work.entity.AdEntity;

public interface AdMapper {

    AdDTO toAdDTO(AdEntity entity);

    ExtendedAdDTO toExtendedAdDTO(AdEntity entity);

    AdEntity toAdEntity(CreateOrUpdateAdDTO dto);
}