package ru.skypro.homework.service;

import ru.skypro.homework.dto.rq.ad.CreateOrUpdateAd;
import ru.skypro.homework.dto.rs.ad.Ad;
import ru.skypro.homework.dto.rs.ad.Ads;
import ru.skypro.homework.dto.rs.ad.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;

public interface AdService {

    Ads getAllAds();

    ExtendedAd getInfoById(Integer id);

    boolean deleteAdById(Integer id);

    Ad updateAdById(Integer id, CreateOrUpdateAd createOrUpdateAd);

    Ads getAdsByUser(String username);

    AdEntity getAdById(Integer id);
}
