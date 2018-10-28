package ru.salenko.mtp.repository;

import org.springframework.data.repository.CrudRepository;
import ru.salenko.mtp.entity.Bank;

public interface BankRepository extends CrudRepository<Bank, Long> {
}
