package ru.salenko.mtp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.salenko.mtp.dto.CityItem;
import ru.salenko.mtp.services.MtpService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/city")
public class CityController {
    private final MtpService mtpService;
    public CityController(MtpService mtpService) {
        this.mtpService = mtpService;
    }

    public List<CityItem> getCitiesByCodes(Set<String> codes) {
        return mtpService.getCitiesByCodes(codes);
    }
}
