package ru.salenko.mtp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.salenko.mtp.dto.BankItem;
import ru.salenko.mtp.services.MtpService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bank")
public class BankController {

    private final MtpService mtpService;

    public BankController(MtpService mtpService) {
        this.mtpService = mtpService;
    }

    @GetMapping("/list")
    public List<BankItem> getBankItems() {
        return mtpService.getBanks();
    }

    public Optional<BankItem> findByCode(String code) {
        return mtpService.findBankByCode(code);
    }
}
