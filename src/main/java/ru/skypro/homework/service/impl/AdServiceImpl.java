package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ad.AdDTO;
import ru.skypro.homework.dto.ad.AdsDTO;
import ru.skypro.homework.dto.ad.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ad.ExtendedAdDTO;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;

@Service
@Validated
@RequiredArgsConstructor
@Transactional(isolation = SERIALIZABLE)
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final UserService userService;
    private final ImageService imageService;
    private final AdMapper adMapper;

    @Override
    public AdsDTO getAllAds() {
        List<AdDTO> list = adRepository.findAll()
                .stream()
                .map(adMapper::toAdDTO)
                .collect(Collectors.toList());

        return new AdsDTO(list.size(), list);
    }

    @Override
    public AdDTO addAd(CreateOrUpdateAdDTO dto,
                       MultipartFile file) {

        AdEntity ad = adMapper.toAdEntity(dto);
        UserEntity user = userService.getUser();
        ImageEntity image = imageService.saveImage(file);

        ad.setAuthor(user);
        ad.setImage(image);

        adRepository.save(ad);
        return adMapper.toAdDTO(ad);
    }

    @Override
    public ExtendedAdDTO getInfoAboutAd(int id) {
        return adMapper.toExtendedAdDTO(getById(id));
    }

    @Override
    @PreAuthorize(value = "hasRole('ADMIN')" +
            "or @adServiceImpl.isAuthor(authentication.getName, #id)")
    public boolean deleteAd(int id) {
        AdEntity ad = getById(id);
        adRepository.delete(ad);
        imageService.deleteImage(ad.getImage().getId());
        return true;
    }

    @Override
    @PreAuthorize(value = "@adServiceImpl.isAuthor(authentication.getName, #id)")
    public AdDTO updateInfoAboutAd(int id, CreateOrUpdateAdDTO dto) {
        AdEntity ad = getById(id);

        ad.setTitle(dto.getTitle());
        ad.setPrice(dto.getPrice());
        ad.setDescription(dto.getDescription());

        adRepository.save(ad);
        return adMapper.toAdDTO(ad);
    }

    @Override
    public AdsDTO getAllAdsOfUser() {
        UserEntity user = userService.getUser();

        List<AdDTO> list = adRepository.findAdEntitiesByAuthor(user)
                .stream()
                .map(adMapper::toAdDTO)
                .collect(Collectors.toList());

        return new AdsDTO(list.size(), list);
    }

    @Override
    @PreAuthorize(value = "@adServiceImpl.isAuthor(authentication.getName, #id)")
    public String updateImageOfAd(int id, MultipartFile file) {
        AdEntity ad = getById(id);
        ImageEntity image = imageService.saveImage(file);
        ad.setImage(image);
        adRepository.save(ad);
        return image.getPath();
    }

    @Override
    public AdEntity getById(int id) {
        return adRepository.findById(id)
                .orElseThrow(AdNotFoundException::new);
    }

    /**
     * Проверка соответствия автора объявления с текущим пользователем
     *
     * @param username <code> Authentication.getName </code>
     * @param id       <i> ID объявления </i>
     * @return {@link Boolean} <i> Результат выполнения метода </i>
     */
    public boolean isAuthor(String username, int id) {
        AdEntity ad = getById(id);
        return ad.getAuthor().getUsername().equals(username);
    }
}