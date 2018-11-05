package ru.salenko.mtp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.salenko.mtp.dto.CityItem;
import ru.salenko.mtp.dto.CountryItem;
import ru.salenko.mtp.services.MtpService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/country")
public class CountryController {
    private final MtpService mtpService;
    public CountryController(MtpService mtpService) {
        this.mtpService = mtpService;
    }

    public List<CountryItem> getCountriesByCities(List<CityItem> cities) {
        return mtpService.getCountriesByCodes(cities.stream().map(CityItem::getCountryCode).collect(Collectors.toSet()));
    }
}
