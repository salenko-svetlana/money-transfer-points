package ru.salenko.mtp.controller;

import org.springframework.web.bind.annotation.*;
import ru.salenko.mtp.dto.PointItem;
import ru.salenko.mtp.services.MtpService;

import java.util.List;

@RestController
@RequestMapping("/api/point")
public class PointController {

    private final MtpService mtpService;
    public PointController(MtpService mtpService) { this.mtpService = mtpService; }

    @PostMapping(value = "/findAllByBank")
    public List<PointItem> findAllByBank(@RequestBody String bankCode) {
       return mtpService.findPointsByBankCode(bankCode);
    }
}
