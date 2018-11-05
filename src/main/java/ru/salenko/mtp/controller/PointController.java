package ru.salenko.mtp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.salenko.mtp.dto.PointItem;
import ru.salenko.mtp.services.MtpService;

import java.util.List;

@RestController
@RequestMapping("/api/point")
public class PointController {

    private final MtpService mtpService;
    public PointController(MtpService mtpService) { this.mtpService = mtpService; }

    @GetMapping("/findAllByBank")
    public List<PointItem> findAllByBank(String bankCode) {
       return mtpService.findPointsByBankCode(bankCode);
    }
}
