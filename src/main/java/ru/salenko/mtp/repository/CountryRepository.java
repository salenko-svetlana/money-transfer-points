package ru.salenko.mtp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.salenko.mtp.entity.Country;

import java.util.List;
import java.util.Set;

public interface CountryRepository extends JpaRepository<Country, Long> {
    List<Country> findAllByCodeIn(Set<String> codes);
}
