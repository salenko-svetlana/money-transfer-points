package ru.salenko.mtp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.salenko.mtp.entity.Bank;

import java.util.Optional;

/**
 * Репозиторий для работы с сущностью Банк
 */
public interface BankRepository extends JpaRepository<Bank, Long> {
    /**
     * Поиск банка по коду
     * @param code код для поиска
     * @return банк, если найден, иначе пусто
     */
    Optional<Bank> findBankByCode(final String code);
}
