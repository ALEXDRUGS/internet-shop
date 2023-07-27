package ru.skypro.homework.controllers;

import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.services.impl.AdsService;
import ru.skypro.homework.services.impl.AuthServiceImpl;

import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:3000")
public class AdsController {
    private final AdsService adsService;

    public AdsController(AdsService adsService) {
        this.adsService = adsService;
    }

    /**
     * Получение списка объявлений
     *
     * @return List of AdDto instance
     */
    @GetMapping("/ads")
    public List<AdDto> getAll() {
        return adsService.getAllAds();
    }

    /**
     * Создание объявления
     *
     * @param properties CreateOrUpdateAdDto
     * @param image      MultipartFile
     * @return AdDto instance
     */
    @SneakyThrows
    @PostMapping("/ads")
    public AdDto addAd(CreateOrUpdateAdDto properties, MultipartFile image) {
        return adsService.createAd(properties, image);
    }

    /**
     * Получение расширенной информации объявления
     *
     * @param id Integer
     * @return ExtendedAdDto
     */
    @GetMapping("/ads/{id}")
    public ExtendedAdDto getAd(@PathVariable Integer id) {
        return adsService.getAdInfo(id);
    }

    /**
     * Удаление объявления
     *
     * @param id Integer
     */
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/ads/{id}")
    public void deleteAd(@PathVariable Integer id) {
        if (isAuthorities(id)) {
            adsService.deleteAd(id);
        }
    }

    /**
     * Обновление объявления
     *
     * @param dto CreateOrUpdateAdDto
     * @param id  Integer
     * @return AdDto
     */
    @PatchMapping("/ads/{id}")
    public AdDto updateAd(@RequestBody CreateOrUpdateAdDto dto, @PathVariable Integer id) {
        if (isAuthorities(id)) {
            return adsService.updateAd(dto, id);
        }
        throw new HttpClientErrorException(HttpStatus.FORBIDDEN);
    }

    /**
     * Получение списка объявлений авторизованного пользователя
     *
     * @return List of AdDto instance
     */
    @GetMapping("/ads/me")
    public List<AdDto> getUserAds() {
        return adsService.getUserAds();
    }

    /**
     * Обновление картинки объявления
     *
     * @param image MultipartFile
     * @param id    Integer
     */
    @SneakyThrows
    @PatchMapping("/ads/{id}/image")
    public void updateAdImage(@RequestBody MultipartFile image, @PathVariable Integer id) {
        adsService.updateAdImage(image, id);
    }

    /**
     * Авторизация пользователя
     *
     * @param id Integer
     * @return true
     */
    private boolean isAuthorities(Integer id) {
        return adsService.getByAdId(id).getUser().equals(AuthServiceImpl.getAuthUser())
                || adsService.getByAdId(id).getUser().getRole().name().equals("ADMIN");
    }
}