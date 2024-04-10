package ru.skypro.homework.utils.mapper.ad;

import ru.skypro.homework.dto.ad.AdDTO;
import ru.skypro.homework.dto.ad.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ad.ExtendedAdDTO;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;

import static ru.skypro.homework.utils.mapper.Constants.*;

public class MappingToAdEntity {

    public static AdDTO createAdDTO() {
        AdDTO dto = new AdDTO();

        dto.setAuthor(ID);
        dto.setImage(FILE_PATH);
        dto.setPk(ID);
        dto.setPrice(PRICE);
        dto.setTitle(TITLE);

        return dto;
    }

    public static CreateOrUpdateAdDTO createCreateOrUpdateAdDTO() {
        CreateOrUpdateAdDTO dto = new CreateOrUpdateAdDTO();

        dto.setTitle(TITLE);
        dto.setPrice(PRICE);
        dto.setDescription(DESCRIPTION);

        return dto;
    }

    public static ExtendedAdDTO createExtendedAdDTO() {
        ExtendedAdDTO dto = new ExtendedAdDTO();

        dto.setPk(1);
        dto.setAuthorFirstName(FIRST_NAME);
        dto.setAuthorLastName(LAST_NAME);
        dto.setDescription(DESCRIPTION);
        dto.setEmail(USERNAME);
        dto.setImage(FILE_PATH);
        dto.setPhone(PHONE_NUMBER);
        dto.setPrice(PRICE);
        dto.setTitle(TITLE);

        return dto;
    }

    public static AdEntity createAdEntityFromAdDTO() {
        AdDTO dto = createAdDTO();
        AdEntity entity = new AdEntity();

        entity.setAuthor(new UserEntity());
        entity.setImage(new ImageEntity());

        entity.getAuthor().setId(dto.getAuthor());
        entity.getImage().setFilePath(dto.getImage());
        entity.setId(dto.getPk());
        entity.setPrice(dto.getPrice());
        entity.setTitle(dto.getTitle());

        return entity;
    }

    public static AdEntity createAdEntityFromCreateOrUpdateAdDTO() {
        CreateOrUpdateAdDTO dto = createCreateOrUpdateAdDTO();
        AdEntity entity = new AdEntity();

        entity.setTitle(dto.getTitle());
        entity.setPrice(dto.getPrice());
        entity.setDescription(dto.getDescription());

        return entity;
    }

    public static AdEntity createAdEntityFromExtendedAdDTO() {
        ExtendedAdDTO dto = createExtendedAdDTO();
        AdEntity entity = new AdEntity();

        entity.setAuthor(new UserEntity());
        entity.setImage(new ImageEntity());

        entity.setId(dto.getPk());
        entity.getAuthor().setFirstName(dto.getAuthorFirstName());
        entity.getAuthor().setLastName(dto.getAuthorLastName());
        entity.setDescription(dto.getDescription());
        entity.getAuthor().setUsername(dto.getEmail());
        entity.getImage().setFilePath(dto.getImage());
        entity.getAuthor().setPhoneNumber(dto.getPhone());
        entity.setPrice(dto.getPrice());
        entity.setTitle(dto.getTitle());

        return entity;
    }
}