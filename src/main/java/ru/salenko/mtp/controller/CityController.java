package ru.salenko.mtp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.salenko.mtp.dto.CityItem;
import ru.salenko.mtp.dto.PointItem;
import ru.salenko.mtp.services.MtpService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Контроллер для выполнения операций с городами
 */
@RestController
@RequestMapping("/api/city")
public class CityController {
    private final MtpService mtpService;
    public CityController(MtpService mtpService) {
        this.mtpService = mtpService;
    }

    @GetMapping("/byPoints")
    public List<CityItem> getCitiesByPoints(List<PointItem> points) {
        return mtpService.getCitiesByCodes(points.stream().map(PointItem::getCityCode).collect(Collectors.toSet()));
    }
}
