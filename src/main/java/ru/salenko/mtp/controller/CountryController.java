package ru.salenko.mtp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.salenko.mtp.entity.Country;
import ru.salenko.mtp.repository.CountryRepository;

@RestController
@RequestMapping("/api/country")
public class CountryController {

    private final CountryRepository countryRepository;

    public CountryController(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @GetMapping("/list")
    public Iterable<Country> getCountry() {
        return countryRepository.findAll();
    }
}
