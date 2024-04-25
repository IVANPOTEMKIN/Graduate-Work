package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ad.AdDTO;
import ru.skypro.homework.dto.ad.AdsDTO;
import ru.skypro.homework.dto.ad.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ad.ExtendedAdDTO;
import ru.skypro.homework.entity.AdEntity;

import javax.validation.Valid;

public interface AdService {

    /**
     * Получение всех объявлений
     *
     * @return {@link AdsDTO}
     */
    AdsDTO getAllAds();

    /**
     * Добавление объявления
     *
     * @param dto  {@link CreateOrUpdateAdDTO}
     * @param file {@link MultipartFile}
     * @return {@link AdDTO}
     */
    AdDTO addAd(@Valid CreateOrUpdateAdDTO dto,
                MultipartFile file);

    /**
     * Получение информации об объявлении
     *
     * @param id <i> ID объявления </i>
     * @return {@link ExtendedAdDTO}
     */
    ExtendedAdDTO getInfoAboutAd(int id);

    /**
     * Удаление объявления
     *
     * @param id <i> ID объявления </i>
     * @return {@link Boolean} <i> Результат выполнения метода </i>
     */
    boolean deleteAd(int id);

    /**
     * Обновление информации об объявлении
     *
     * @param id  <i> ID объявления </i>
     * @param dto {@link CreateOrUpdateAdDTO}
     * @return {@link AdDTO}
     */
    AdDTO updateInfoAboutAd(int id, @Valid CreateOrUpdateAdDTO dto);

    /**
     * Получение объявлений авторизованного пользователя
     *
     * @return {@link AdsDTO}
     */
    AdsDTO getAllAdsOfUser();

    /**
     * Обновление картинки объявления
     *
     * @param id   <i> ID объявления </i>
     * @param file {@link MultipartFile}
     * @return {@link String} <code> Image.getPath() </code>
     */
    String updateImageOfAd(int id, MultipartFile file);

    /**
     * Получение объявления по его ID
     *
     * @param id <i> ID объявления </i>
     * @return {@link AdEntity}
     */
    AdEntity getById(int id);
}