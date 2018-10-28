package ru.salenko.mtp;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface CountryRepository extends JpaRepository<Country, Long> {
    Country findByCode(final String code);

    List<Country> findByCodeStartsWithIgnoreCase(final String code);
}
