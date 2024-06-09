package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.rq.ad.CreateOrUpdateAd;
import ru.skypro.homework.dto.rs.ad.Ad;
import ru.skypro.homework.dto.rs.ad.Ads;
import ru.skypro.homework.dto.rs.ad.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdServiceImpl implements AdService {
    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final AdMapper mapper;

    public AdServiceImpl(AdRepository adRepository, UserRepository userRepository, AdMapper mapper) {
        this.adRepository = adRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }


    @Override
    public Ads getAllAds() {
        List<Ad> allAds = adRepository.findAll().stream().map(mapper::adEntityToAd).collect(Collectors.toList());
        return new Ads(allAds.size(), allAds);
    }

    @Override
    public ExtendedAd getInfoById(Integer id) {
        Optional<AdEntity> awd = adRepository.findById(id);
        if (awd.isEmpty()) {
            throw new AdNotFoundException("Not found ad");
        }
        return mapper.adEntityToExtendedAd(awd.get());
    }

    @Override
    public boolean deleteAdById(Integer id) {
        Optional<AdEntity> awd = adRepository.findById(id);
        if (awd.isEmpty()) {
            return false;
        }
        adRepository.deleteById(id);
        return true;
    }

    @Override
    public Ad updateAdById(Integer id, CreateOrUpdateAd createOrUpdateAd) {
        Optional<AdEntity> adEntityOptional = adRepository.findById(id);
        if (adEntityOptional.isPresent()) {
            AdEntity adEntity = adEntityOptional.get();
            adEntity.setTitle(createOrUpdateAd.getTitle());
            adEntity.setDescription(createOrUpdateAd.getDescription());
            adEntity.setPrice(createOrUpdateAd.getPrice());
            adRepository.save(adEntity);
            return mapper.adEntityToAd(adEntity);
        }
        throw new AdNotFoundException("Not found ad");
    }

    @Override
    public Ads getAdsByUser(String username) {
        Optional<UserEntity> userEntity = userRepository.findByUsername(username);
        if (userEntity.isPresent()) {
            List<Ad> adList = userEntity.get().getAds().stream().map(mapper::adEntityToAd).collect(Collectors.toList());
            return new Ads(adList.size(), adList);
        }
        throw new AdNotFoundException("No found User");
    }

    @Override
    public AdEntity getAdById(Integer id) {
        return adRepository.findById(id).orElseThrow(AdNotFoundException::new);
    }

}
