package ru.salenko.mtp.repository;

import org.springframework.data.repository.CrudRepository;
import ru.salenko.mtp.entity.City;

public interface CityRepository extends CrudRepository<City, Long> {
}
