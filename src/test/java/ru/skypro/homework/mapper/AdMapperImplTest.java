package ru.skypro.homework.mapper;

import org.junit.jupiter.api.Test;
import ru.skypro.homework.dto.ad.AdDTO;
import ru.skypro.homework.dto.ad.ExtendedAdDTO;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.mapper.impl.AdMapperImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static ru.skypro.homework.utils.mapper.AdMapperUtils.*;

class AdMapperImplTest {

    private final AdMapper mapper = new AdMapperImpl();

    @Test
    void toAdDTO() {
        AdDTO expected = getAdDTO_From_AdEntity();
        AdDTO actual = mapper.toAdDTO(createAdEntity());

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    void toExtendedAdDTO() {
        ExtendedAdDTO expected = getExtendedAdDTO_From_AdEntity();
        ExtendedAdDTO actual = mapper.toExtendedAdDTO(createAdEntity());

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    void toAdEntity() {
        AdEntity expected = getAdEntity_From_CreateOrUpdateAdDTO();
        AdEntity actual = mapper.toAdEntity(createDTO());

        assertNotNull(actual);
        assertEquals(expected, actual);
    }
}