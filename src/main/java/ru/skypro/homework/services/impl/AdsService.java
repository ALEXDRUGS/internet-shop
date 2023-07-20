package ru.skypro.homework.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.repositories.AdsRepository;
import ru.skypro.homework.utils.MappingUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdsService {
    private final AdsRepository adsRepository;
    private final MappingUtils mappingUtils;
    private final ImageService imageService;

    public AdsService(AdsRepository adsRepository, MappingUtils mappingUtils, ImageService imageService) {
        this.adsRepository = adsRepository;
        this.mappingUtils = mappingUtils;
        this.imageService = imageService;
    }

    public List<Ad> getAllAds() {
        return adsRepository.findAll();
    }

    public AdDto createAd(CreateOrUpdateAdDto createDto, MultipartFile image) {
        adsRepository.saveAndFlush(mappingUtils.mapToAd(createDto, imageService.createImage(image)));
        return mappingUtils.mapToAdDto(adsRepository.getByUserId(AuthServiceImpl.getAuthUser().getId()));
    }

    public ExtendedAdDto getAdInfo(Integer adId) {
        return mappingUtils.mapToExtendedAdDto(adsRepository.getByAdId(adId));
    }

    public void deleteAd(Integer adId) {
        adsRepository.deleteById(adId);
    }

    public AdDto updateAd(CreateOrUpdateAdDto dto, Integer id) {
        return mappingUtils.mapToAdDto(mappingUtils.mapToAd(dto, adsRepository.getByAdId(id).getImage()));
    }

    public List<AdDto> getUserAds() {
        return adsRepository
                .findAllByUserId(AuthServiceImpl.getAuthUser().getId())
                .stream()
                .map(mappingUtils::mapToAdDto)
                .collect(Collectors.toList());
    }

    public void updateAdImage(MultipartFile image, Integer id) {
        adsRepository.getByAdId(id).setImage(imageService.createImage(image));
    }

    public Ad getByAdId(Integer adId) {
        return adsRepository.getByAdId(adId);
    }
}