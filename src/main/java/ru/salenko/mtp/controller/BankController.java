package ru.salenko.mtp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.salenko.mtp.entity.Bank;
import ru.salenko.mtp.repository.BankRepository;

@RestController
@RequestMapping("/api/bank")
public class BankController {

    private final BankRepository bankRepository;
    public BankController(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @GetMapping("/list")
    public Iterable<Bank> getBank() {
        return bankRepository.findAll();
    }

}
