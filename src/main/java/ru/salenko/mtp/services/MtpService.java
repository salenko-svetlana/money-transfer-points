package ru.salenko.mtp.services;

import org.springframework.stereotype.Service;
import ru.salenko.mtp.dto.BankItem;
import ru.salenko.mtp.dto.CityItem;
import ru.salenko.mtp.dto.CountryItem;
import ru.salenko.mtp.dto.PointItem;
import ru.salenko.mtp.entity.Bank;
import ru.salenko.mtp.repository.BankRepository;
import ru.salenko.mtp.repository.CityRepository;
import ru.salenko.mtp.repository.CountryRepository;
import ru.salenko.mtp.repository.PointRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Здесь в будущем будет реализован сервис по обработке запросов от контроллеров
 */
@Service
public class MtpService {

    private final BankRepository bankRepository;
    private final PointRepository pointRepository;
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    public MtpService(BankRepository bankRepository, PointRepository pointRepository, CityRepository cityRepository, CountryRepository countryRepository){
        this.bankRepository = bankRepository;
        this.pointRepository = pointRepository;
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
    }

    public List<BankItem> getBanks() {
        return bankRepository.findAll().stream()
                .map(bank -> new BankItem(bank.getCode(), bank.getName())).collect(Collectors.toList());
    }

    public Optional<BankItem> findBankByCode(String code) {
        return bankRepository.findBankByCode(code).map( bank -> new BankItem(bank.getCode(), bank.getName()));
    }

    public List<PointItem> findPointsByBankCode(String bankCode) {
        Optional<Bank> bank = bankRepository.findBankByCode(bankCode);
        if (bank.isPresent()){
            return pointRepository.findAllByBank(bank.get()).stream()
                    .map(point -> new PointItem(point.getCode(), point.getName(), point.getCity().getCode())).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public List<CityItem> getCitiesByCodes(Set<String> codes) {
        return cityRepository.findAllByCodeIn(codes).stream()
                .map(city -> new CityItem(city.getCode(), city.getName(), city.getCountry().getCode())).collect(Collectors.toList());
    }

    public List<CountryItem> getCountriesByCodes(Set<String> codes) {
        return countryRepository.findAllByCodeIn(codes).stream()
                .map(country -> new CountryItem(country.getCode(), country.getName())).collect(Collectors.toList());

    }

    public void deleteBank(String code) {
        Optional<Bank> bank = bankRepository.findBankByCode(code);
        if (bank.isPresent()){
            bankRepository.delete(bank.get());
        }
    }

    public void createBank(BankItem bank) {
        Bank newBank = new Bank(bank.getCode(), bank.getName());
        bankRepository.save(newBank);
    }
}
