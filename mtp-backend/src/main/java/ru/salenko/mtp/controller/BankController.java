package ru.salenko.mtp.controller;

import org.springframework.web.bind.annotation.*;
import ru.salenko.mtp.dto.BankItem;
import ru.salenko.mtp.services.MtpService;

import java.util.List;
import java.util.Optional;

/**
 * Контроллер для выполнения операций с банками
 */
@RestController
@RequestMapping(value = "/api/bank")
public class BankController {

    private final MtpService mtpService;

    public BankController(MtpService mtpService) {
        this.mtpService = mtpService;
    }

    @GetMapping("/list")
    public List<BankItem> getBankItems() {
        return mtpService.getBanks();
    }

    @PostMapping(value = "/findByCode")
    public Optional<BankItem> findByCode(@RequestBody String code) {
        return mtpService.findBankByCode(code);
    }

    public void delete(String code) {
        mtpService.deleteBank(code);
    }

    @PutMapping(value = "/save")
    public void save(@RequestBody BankItem bank) {
        mtpService.createBank(bank);
    }
}
