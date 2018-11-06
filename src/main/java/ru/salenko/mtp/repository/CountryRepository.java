package ru.salenko.mtp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.salenko.mtp.entity.Country;

import java.util.List;
import java.util.Set;

/**
 * Репозиторий для работы с сущностью Страна
 */
public interface CountryRepository extends JpaRepository<Country, Long> {
    /**
     * Поиск страны по кодам
     * @param codes коды для поиска
     * @return список стран с указанными кодами
     */
    List<Country> findAllByCodeIn(Set<String> codes);
}
