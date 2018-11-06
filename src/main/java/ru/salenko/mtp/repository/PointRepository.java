package ru.salenko.mtp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.salenko.mtp.entity.Bank;
import ru.salenko.mtp.entity.Point;

import java.util.List;

/**
 * Репозиторий для работы с сущностью Точка денежных переводов
 */
public interface PointRepository extends JpaRepository<Point, Long> {
    /**
     * Поиск точек денежных переводов по банку
     * @param bank банк
     * @return список точек
     */
    List<Point> findAllByBank(Bank bank);
}
