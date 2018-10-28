package ru.salenko.mtp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.salenko.mtp.entity.Country;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Long> {
    Country findByCode(final String code);

    List<Country> findByCodeStartsWithIgnoreCase(final String code);
}
