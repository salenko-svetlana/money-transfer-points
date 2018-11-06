package ru.salenko.mtp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.salenko.mtp.entity.City;

import java.util.List;
import java.util.Set;

/**
 * Репозиторий для работы с сущностью Город
 */
public interface CityRepository extends JpaRepository<City, Long> {
    /**
     * Поик города по кодам
     * @param codes коды для поиска
     * @return список городов с указанными кодами
     */
    List<City> findAllByCodeIn(Set<String> codes);
}
