package com.example.graduate_work.mapper;

import com.example.graduate_work.dto.ad.AdDTO;
import com.example.graduate_work.dto.ad.ExtendedAdDTO;
import com.example.graduate_work.entity.AdEntity;
import com.example.graduate_work.mapper.impl.AdMapperImpl;
import org.junit.jupiter.api.Test;

import static com.example.graduate_work.utils.Examples.createAdEntity;
import static com.example.graduate_work.utils.Examples.createCreateOrUpdateAdDTO;
import static com.example.graduate_work.utils.mapper.AdMapperUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
        AdEntity actual = mapper.toAdEntity(createCreateOrUpdateAdDTO());

        assertNotNull(actual);
        assertEquals(expected, actual);
    }
}