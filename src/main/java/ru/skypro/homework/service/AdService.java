package ru.skypro.homework.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ad.AdDTO;
import ru.skypro.homework.dto.ad.AdsDTO;
import ru.skypro.homework.dto.ad.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ad.ExtendedAdDTO;
import ru.skypro.homework.entity.AdEntity;

public interface AdService {

    ResponseEntity<AdsDTO> getAllAds();

    ResponseEntity<AdDTO> AddAd(CreateOrUpdateAdDTO properties,
                                MultipartFile image,
                                Authentication auth);

    ResponseEntity<ExtendedAdDTO> getInfoAboutAd(int id);

    ResponseEntity<?> deleteAd(int id);

    ResponseEntity<AdDTO> updateInfoAboutAd(int id, CreateOrUpdateAdDTO dto);

    ResponseEntity<AdsDTO> getAllAdsOfUser(Authentication auth);

    ResponseEntity<String> updateImageOfAd(int id, MultipartFile image);

    AdEntity getById(int id);
}