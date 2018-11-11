package ru.salenko.mtp.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.salenko.mtp.dto.BankItem;
import ru.salenko.mtp.dto.CityItem;
import ru.salenko.mtp.dto.CountryItem;
import ru.salenko.mtp.dto.PointItem;

import java.util.List;
import java.util.Optional;

@Component
class ApiCaller {
    private final RestTemplate restTemplate;
    private final String url;
    private static final String PROTOCOL = "http";

    @Autowired
    ApiCaller(@Value("${MTPFrontend.backend-host}") String host,
              @Value("${MTPFrontend.backend-port}") Integer port) {
        this.url = host + ":" + port;
        this.restTemplate = new RestTemplate();
    }

    List<BankItem> getBankItems(){
        return restTemplate.exchange(buildUrl("/api/bank/list"), HttpMethod.GET, null,
                new ParameterizedTypeReference<List<BankItem>>() {
                }).getBody();
    }

    void save(BankItem bank) {
        restTemplate.exchange(buildUrl("/api/bank/save"), HttpMethod.PUT, new HttpEntity<>(bank), Void.class);
    }

    Optional<BankItem> findBankByCode(String code) {
          return restTemplate.exchange(buildUrl("/api/bank/findByCode"), HttpMethod.POST, new HttpEntity<>(code),
                new ParameterizedTypeReference<Optional<BankItem>>() {
                }).getBody();
    }

    List<PointItem> findAllPointsByBank(String code) {
        return restTemplate.exchange(buildUrl("/api/point/findAllByBank"), HttpMethod.POST, new HttpEntity<>(code),
                new ParameterizedTypeReference<List<PointItem>>() {
                }).getBody();
    }

    List<CityItem> getCitiesByPoints(List<PointItem> points) {
        return restTemplate.exchange(buildUrl("/api/city/byPoints"), HttpMethod.POST, new HttpEntity<>(points),
                new ParameterizedTypeReference<List<CityItem>>() {
                }).getBody();
    }

    List<CountryItem> getCountriesByCities(List<CityItem> cities) {
        return restTemplate.exchange(buildUrl("/api/country/byCities"), HttpMethod.POST, new HttpEntity<>(cities),
                new ParameterizedTypeReference<List<CountryItem>>() {
                }).getBody();
    }

    private String buildUrl(String method) {
        return PROTOCOL + "://" + url + method;
    }
}
