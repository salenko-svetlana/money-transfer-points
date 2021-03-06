package ru.salenko.mtp.controller;

import org.springframework.web.bind.annotation.*;
import ru.salenko.mtp.dto.CityItem;
import ru.salenko.mtp.dto.CountryItem;
import ru.salenko.mtp.services.MtpService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Контроллер для выполнения операций со странами
 */
@RestController
@RequestMapping("/api/country")
public class CountryController {
    private final MtpService mtpService;
    public CountryController(MtpService mtpService) {
        this.mtpService = mtpService;
    }

    @PostMapping(value = "/byCities")
    public List<CountryItem> getCountriesByCities(@RequestBody List<CityItem> cities) {
        return mtpService.getCountriesByCodes(cities.stream().map(CityItem::getCountryCode).collect(Collectors.toSet()));
    }
}
