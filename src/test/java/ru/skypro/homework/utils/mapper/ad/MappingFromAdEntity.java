package ru.skypro.homework.utils.mapper.ad;

import ru.skypro.homework.dto.ad.AdDTO;
import ru.skypro.homework.dto.ad.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ad.ExtendedAdDTO;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;

import static ru.skypro.homework.utils.mapper.Constants.*;

public class MappingFromAdEntity {

    public static AdEntity createAdEntity() {
        AdEntity entity = new AdEntity();

        entity.setImage(new ImageEntity());
        entity.setAuthor(new UserEntity());

        entity.setId(ID);
        entity.setTitle(TITLE);
        entity.setPrice(PRICE);
        entity.setDescription(DESCRIPTION);
        entity.getImage().setFilePath(FILE_PATH);
        entity.getAuthor().setId(ID);
        entity.getAuthor().setFirstName(FIRST_NAME);
        entity.getAuthor().setLastName(LAST_NAME);
        entity.getAuthor().setUsername(USERNAME);
        entity.getAuthor().setPhoneNumber(PHONE_NUMBER);

        return entity;
    }

    public static AdDTO createAdDTOFromAdEntity() {
        AdEntity entity = createAdEntity();
        AdDTO dto = new AdDTO();

        dto.setAuthor(entity.getAuthor().getId());
        dto.setImage(entity.getImage().getFilePath());
        dto.setPk(entity.getId());
        dto.setPrice(entity.getPrice());
        dto.setTitle(entity.getTitle());

        return dto;
    }

    public static CreateOrUpdateAdDTO createCreateOrUpdateAdDTOFromAdEntity() {
        AdEntity entity = createAdEntity();
        CreateOrUpdateAdDTO dto = new CreateOrUpdateAdDTO();

        dto.setTitle(entity.getTitle());
        dto.setPrice(entity.getPrice());
        dto.setDescription(entity.getDescription());

        return dto;
    }

    public static ExtendedAdDTO createExtendedAdDTOFromAdEntity() {
        AdEntity entity = createAdEntity();
        ExtendedAdDTO dto = new ExtendedAdDTO();

        dto.setPk(entity.getId());
        dto.setAuthorFirstName(entity.getAuthor().getFirstName());
        dto.setAuthorLastName(entity.getAuthor().getLastName());
        dto.setDescription(entity.getDescription());
        dto.setEmail(entity.getAuthor().getUsername());
        dto.setImage(entity.getImage().getFilePath());
        dto.setPhone(entity.getAuthor().getPhoneNumber());
        dto.setPrice(entity.getPrice());
        dto.setTitle(entity.getTitle());

        return dto;
    }
}