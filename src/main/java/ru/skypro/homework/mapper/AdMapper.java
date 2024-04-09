package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.ad.AdDTO;
import ru.skypro.homework.dto.ad.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ad.ExtendedAdDTO;
import ru.skypro.homework.entity.AdEntity;

@Mapper(componentModel = "spring")
public interface AdMapper {

    AdMapper INSTANCE = Mappers.getMapper(AdMapper.class);

    /*
    Entity -> DTO
     */

    @Mapping(target = "author", source = "entity.author.id")
    @Mapping(target = "image", source = "entity.image.filePath")
    @Mapping(target = "pk", source = "entity.id")
    AdDTO toAdDTO(AdEntity entity);

    CreateOrUpdateAdDTO toCreateOrUpdateAdDTO(AdEntity entity);

    @Mapping(target = "pk", source = "entity.id")
    @Mapping(target = "authorFirstName", source = "entity.author.firstName")
    @Mapping(target = "authorLastName", source = "entity.author.lastName")
    @Mapping(target = "email", source = "entity.author.username")
    @Mapping(target = "image", source = "entity.image.filePath")
    @Mapping(target = "phone", source = "entity.author.phoneNumber")
    ExtendedAdDTO toExtendedAdDTO(AdEntity entity);

    /*
    DTO -> Entity
     */

    @Mapping(target = "author.id", source = "dto.author")
    @Mapping(target = "image.filePath", source = "dto.image")
    @Mapping(target = "id", source = "dto.pk")
    AdEntity toAdDTO(AdDTO dto);

    AdEntity toCreateOrUpdateAdDTO(CreateOrUpdateAdDTO dto);

    @Mapping(target = "id", source = "dto.pk")
    @Mapping(target = "author.firstName", source = "dto.authorFirstName")
    @Mapping(target = "author.lastName", source = "dto.authorLastName")
    @Mapping(target = "author.username", source = "dto.email")
    @Mapping(target = "image.filePath", source = "dto.image")
    @Mapping(target = "author.phoneNumber", source = "dto.phone")
    AdEntity toExtendedAdDTO(ExtendedAdDTO dto);
}