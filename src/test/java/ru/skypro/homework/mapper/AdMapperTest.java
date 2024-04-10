package ru.skypro.homework.mapper;

import org.junit.jupiter.api.Test;
import ru.skypro.homework.dto.ad.AdDTO;
import ru.skypro.homework.dto.ad.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ad.ExtendedAdDTO;
import ru.skypro.homework.entity.AdEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static ru.skypro.homework.mapper.AdMapper.INSTANCE;
import static ru.skypro.homework.utils.mapper.ad.MappingFromAdEntity.*;
import static ru.skypro.homework.utils.mapper.ad.MappingToAdEntity.*;

public class AdMapperTest {

    @Test
    void to_AdDTO_From_AdEntity() {
        AdEntity entity = createAdEntity();

        AdDTO expected = createAdDTOFromAdEntity();
        AdDTO actual = INSTANCE.toAdDTO(entity);

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    void to_CreateOrUpdateAdDTO_From_AdEntity() {
        AdEntity entity = createAdEntity();

        CreateOrUpdateAdDTO expected = createCreateOrUpdateAdDTOFromAdEntity();
        CreateOrUpdateAdDTO actual = INSTANCE.toCreateOrUpdateAdDTO(entity);

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    void to_ExtendedAdDTO_From_AdEntity() {
        AdEntity entity = createAdEntity();

        ExtendedAdDTO expected = createExtendedAdDTOFromAdEntity();
        ExtendedAdDTO actual = INSTANCE.toExtendedAdDTO(entity);

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    void to_AdEntity_From_AdDTO() {
        AdDTO dto = createAdDTO();

        AdEntity expected = createAdEntityFromAdDTO();
        AdEntity actual = INSTANCE.toAdEntity(dto);

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    void to_AdEntity_From_CreateOrUpdateAdDTO() {
        CreateOrUpdateAdDTO dto = createCreateOrUpdateAdDTO();

        AdEntity expected = createAdEntityFromCreateOrUpdateAdDTO();
        AdEntity actual = INSTANCE.toAdEntity(dto);

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    void to_AdEntity_From_ExtendedAdDTO() {
        ExtendedAdDTO dto = createExtendedAdDTO();

        AdEntity expected = createAdEntityFromExtendedAdDTO();
        AdEntity actual = INSTANCE.toAdEntity(dto);

        assertNotNull(actual);
        assertEquals(expected, actual);
    }
}