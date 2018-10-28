package ru.salenko.mtp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.salenko.mtp.entity.City;
import ru.salenko.mtp.repository.CityRepository;

@RestController
@RequestMapping("/api/city")
public class CityController {

    private final CityRepository cityRepository;

    public CityController(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }


    @GetMapping("/list")
    public Iterable<City> getCity() {
        return cityRepository.findAll();
    }
}
