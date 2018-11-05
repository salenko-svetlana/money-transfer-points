package ru.salenko.mtp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Информация о городе
 */
@Getter
@AllArgsConstructor
public class CityItem {
    private String code;
    private String name;
    private String countryCode;
}
