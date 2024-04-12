package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ad.AdDTO;
import ru.skypro.homework.dto.ad.AdsDTO;
import ru.skypro.homework.dto.ad.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ad.ExtendedAdDTO;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static ru.skypro.homework.mapper.AdMapper.INSTANCE;

@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final UserService userService;
    private final ImageService imageService;

    @Override
    public AdsDTO getAllAds() {
        List<AdDTO> dtoList = adRepository.findAll().stream()
                .map(INSTANCE::toAdDTO)
                .collect(Collectors.toList());

        return new AdsDTO(dtoList.size(), dtoList);
    }

    @Override
    public AdDTO AddAd(CreateOrUpdateAdDTO properties,
                       MultipartFile image,
                       Authentication auth) {

        AdEntity adEntity = INSTANCE.toAdEntity(properties);
        UserEntity userEntity = userService.getUser(auth.getName());
        ImageEntity imageEntity = getImage(image);

        adEntity.setAuthor(userEntity);
        adEntity.setImage(imageEntity);

        adRepository.save(adEntity);
        return INSTANCE.toAdDTO(adEntity);
    }

    @Override
    public ExtendedAdDTO getInfoAboutAd(int id) {
        return INSTANCE.toExtendedAdDTO(getById(id));
    }

    @Override
    public void deleteAd(int id) {
        adRepository.delete(getById(id));
    }

    @Override
    public AdDTO updateInfoAboutAd(int id, CreateOrUpdateAdDTO dto) {
        AdEntity adEntity = getById(id);

        adEntity.setTitle(dto.getTitle());
        adEntity.setPrice(dto.getPrice());
        adEntity.setDescription(dto.getDescription());

        adRepository.save(adEntity);
        return INSTANCE.toAdDTO(adEntity);
    }

    @Override
    public AdsDTO getAllAdsOfUser(Authentication auth) {
        UserEntity userEntity = userService.getUser(auth.getName());

        List<AdDTO> dtoList = adRepository.findAll().stream()
                .map(INSTANCE::toAdDTO)
                .filter(ad -> ad.getAuthor().equals(userEntity.getId()))
                .collect(Collectors.toList());

        return new AdsDTO(dtoList.size(), dtoList);
    }

    @Override
    public String updateImageOfAd(int id, MultipartFile image) {
        AdEntity adEntity = getById(id);
        ImageEntity imageEntity = getImage(image);
        adEntity.setImage(imageEntity);
        adRepository.save(adEntity);
        return imageEntity.getFilePath();
    }

    private ImageEntity getImage(MultipartFile image) {
        ImageEntity imageEntity;

        try {
            imageEntity = imageService.saveFile(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return imageEntity;
    }

    private AdEntity getById(int id) {
        return adRepository.findById(id).orElseThrow();
    }
}