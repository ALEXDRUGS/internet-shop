package ru.skypro.homework.controllers;

import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.model.Ad;
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

    @GetMapping("/ads")
    public List<Ad> getAll() {
        return adsService.getAllAds();
    }

    @SneakyThrows
    @PostMapping("/ads")
    public AdDto addAd(CreateOrUpdateAdDto properties, MultipartFile image) {
        return adsService.createAd(properties, image);
    }

    @GetMapping("/ads/{id}")
    public ExtendedAdDto getAd(@PathVariable Integer id) {
        return adsService.getAdInfo(id);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/ads/{id}")
    public void deleteAd(@PathVariable Integer id) {
        if (adsService.getByAdId(id).getUser().equals(AuthServiceImpl.getAuthUser())
                || adsService.getByAdId(id).getUser().getRole().name().equals("ADMIN")) {
            adsService.deleteAd(id);
        }
    }

    @PatchMapping("/ads/{id}")
    public AdDto updateAd(@RequestBody CreateOrUpdateAdDto dto, @PathVariable Integer id) {
        if (adsService.getByAdId(id).getUser().equals(AuthServiceImpl.getAuthUser())
                || adsService.getByAdId(id).getUser().getRole().name().equals("ADMIN")) {
            return adsService.updateAd(dto, id);
        }
        throw new HttpClientErrorException(HttpStatus.FORBIDDEN);
    }

    @GetMapping("/ads/me")
    public List<AdDto> getUserAds() {
        return adsService.getUserAds();
    }

    @SneakyThrows
    @PatchMapping("/ads/{id}/image")
    public void updateAdImage(@RequestBody MultipartFile image, @PathVariable Integer id) {
        adsService.updateAdImage(image, id);
    }
}