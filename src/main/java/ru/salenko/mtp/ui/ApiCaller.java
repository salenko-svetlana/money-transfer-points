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
public class ApiCaller {
    private final RestTemplate restTemplate;
    private final String url;
    private final static String PROTOCOL = "http";

    @Autowired
    ApiCaller(@Value("${server.address}") String host,
              @Value("${server.port}") Integer port) {
        this.url = host + ":" + port;
        this.restTemplate = new RestTemplate();
    }

    public List<BankItem> getBankItems(){
        return restTemplate.exchange(buildUrl("/api/bank/list"), HttpMethod.GET, null,
                new ParameterizedTypeReference<List<BankItem>>() {
                }).getBody();
    }

    public void save(BankItem bank) {
        restTemplate.exchange(buildUrl("/api/bank/save"), HttpMethod.PUT, new HttpEntity<BankItem>(bank), Void.class);
    }

    public Optional<BankItem> findBankByCode(String code) {
          return restTemplate.exchange(buildUrl("/api/bank/findByCode"), HttpMethod.POST, new HttpEntity<>(code),
                new ParameterizedTypeReference<Optional<BankItem>>() {
                }).getBody();
    }

    public List<PointItem> findAllPointsByBank(String code) {
        return restTemplate.exchange(buildUrl("/api/point/findAllByBank"), HttpMethod.POST, new HttpEntity<>(code),
                new ParameterizedTypeReference<List<PointItem>>() {
                }).getBody();
    }

    public List<CityItem> getCitiesByPoints(List<PointItem> points) {
        return restTemplate.exchange(buildUrl("/api/city/byPoints"), HttpMethod.POST, new HttpEntity<>(points),
                new ParameterizedTypeReference<List<CityItem>>() {
                }).getBody();
    }

    public List<CountryItem> getCountriesByCities(List<CityItem> cities) {
        return restTemplate.exchange(buildUrl("/api/country/byCities"), HttpMethod.POST, new HttpEntity<>(cities),
                new ParameterizedTypeReference<List<CountryItem>>() {
                }).getBody();
    }

    private String buildUrl(String method) {
        return PROTOCOL + "://" + url + method;
    }
}
