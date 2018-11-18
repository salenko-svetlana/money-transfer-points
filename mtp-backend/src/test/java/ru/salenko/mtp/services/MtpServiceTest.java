package ru.salenko.mtp.services;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.salenko.mtp.dto.BankItem;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Log4j2
@RunWith(SpringJUnit4ClassRunner.class)
public class MtpServiceTest {

    public MtpServiceTest()
    {

    }

    @Autowired
    private MtpService mtpService;

    @Test
    public void getBanks() {
/*        log.debug("Test running");
        List<BankItem> bankItemList = mtpService.getBanks();
        log.debug("BankItemList={}", bankItemList);
        assertEquals (5, bankItemList.size());*/
    }

    @Test
    void findBankByCode() {
        assert(true);
    }

    @Test
    void findPointsByBankCode() {
    }

    @Test
    void getCitiesByCodes() {
    }

    @Test
    void getCountriesByCodes() {
    }

    @Test
    void deleteBank() {
    }

    @Test
    void createBank() {
    }
}