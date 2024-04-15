package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {

    private final AdRepository repository;
    private final UserService userService;
    private final ImageService imageService;
    private final AdMapper mapper;

    @Override
    public ResponseEntity<AdsDTO> getAllAds() {
        List<AdDTO> list = repository.findAll()
                .stream()
                .map(mapper::toAdDTO)
                .collect(Collectors.toList());

        AdsDTO dto = new AdsDTO(list.size(), list);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<AdDTO> AddAd(CreateOrUpdateAdDTO properties,
                                       MultipartFile file,
                                       Authentication auth) {

        AdEntity ad = mapper.toAdEntity(properties);
        UserEntity user = userService.getUser(auth.getName());
        ImageEntity image = imageService.saveImage(file);

        ad.setAuthor(user);
        ad.setImage(image);

        repository.save(ad);
        return ResponseEntity.status(CREATED).body(mapper.toAdDTO(ad));
    }

    @Override
    public ResponseEntity<ExtendedAdDTO> getInfoAboutAd(int id) {
        return ResponseEntity.ok(mapper.toExtendedAdDTO(getById(id)));
    }

    @Override
    public ResponseEntity<?> deleteAd(int id) {
        AdEntity ad = getById(id);
        repository.delete(ad);
        imageService.deleteImage(ad.getImage().getId());
        return ResponseEntity.status(NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<AdDTO> updateInfoAboutAd(int id, CreateOrUpdateAdDTO dto) {
        AdEntity ad = getById(id);

        ad.setTitle(dto.getTitle());
        ad.setPrice(dto.getPrice());
        ad.setDescription(dto.getDescription());

        repository.save(ad);
        return ResponseEntity.ok(mapper.toAdDTO(ad));
    }

    @Override
    public ResponseEntity<AdsDTO> getAllAdsOfUser(Authentication auth) {
        List<AdDTO> list = repository.findAdEntitiesByAuthor_Username(auth.getName())
                .stream()
                .map(mapper::toAdDTO)
                .collect(Collectors.toList());

        AdsDTO dto = new AdsDTO(list.size(), list);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<String> updateImageOfAd(int id, MultipartFile file) {
        AdEntity ad = getById(id);
        ImageEntity image = imageService.saveImage(file);
        ad.setImage(image);
        repository.save(ad);
        return ResponseEntity.ok(image.getPath());
    }

    @Override
    public AdEntity getById(int id) {
        return repository.findById(id).orElseThrow();
    }
}