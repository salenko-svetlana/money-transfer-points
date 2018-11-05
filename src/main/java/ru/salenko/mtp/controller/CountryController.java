package ru.salenko.mtp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.salenko.mtp.dto.CountryItem;
import ru.salenko.mtp.services.MtpService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/country")
public class CountryController {
    private final MtpService mtpService;
    public CountryController(MtpService mtpService) {
        this.mtpService = mtpService;
    }

    public List<CountryItem> getCountriesByCodes(Set<String> codes) {
        return mtpService.getCountriesByCodes(codes);
    }
}
