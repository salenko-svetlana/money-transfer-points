package ru.salenko.mtp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.salenko.mtp.entity.City;

import java.util.List;
import java.util.Set;

public interface CityRepository extends JpaRepository<City, Long> {
    List<City> findAllByCodeIn(Set<String> codes);
}
