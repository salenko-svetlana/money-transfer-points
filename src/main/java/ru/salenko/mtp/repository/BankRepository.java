package ru.salenko.mtp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.salenko.mtp.entity.Bank;

import java.util.List;
import java.util.Optional;

public interface BankRepository extends JpaRepository<Bank, Long> {
    Optional<Bank> findBankByCode(final String code);
}
