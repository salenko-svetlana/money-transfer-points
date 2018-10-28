package ru.salenko.mtp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
