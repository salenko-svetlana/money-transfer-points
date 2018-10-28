package ru.salenko.mtp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.salenko.mtp.entity.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
