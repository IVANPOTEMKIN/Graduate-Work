package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ad.AdDTO;
import ru.skypro.homework.dto.ad.AdsDTO;
import ru.skypro.homework.dto.ad.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ad.ExtendedAdDTO;
import ru.skypro.homework.entity.AdEntity;

import javax.servlet.http.HttpServletResponse;

public interface AdService {

    AdsDTO getAllAds();

    AdDTO AddAd(CreateOrUpdateAdDTO properties,
                MultipartFile image,
                HttpServletResponse response,
                Authentication auth);

    ExtendedAdDTO getInfoAboutAd(int id);

    void deleteAd(int id);

    AdDTO updateInfoAboutAd(int id, CreateOrUpdateAdDTO dto);

    AdsDTO getAllAdsOfUser(Authentication auth);

    String updateImageOfAd(int id, MultipartFile image,
                           HttpServletResponse response);

    AdEntity getById(int id);
}