package ru.salenko.mtp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.salenko.mtp.entity.City;

public interface CityRepository extends JpaRepository<City, Long> {
}
