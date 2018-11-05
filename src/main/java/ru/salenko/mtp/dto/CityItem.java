package ru.salenko.mtp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Информация о городе
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CityItem {
    private String code;
    private String name;
    private String countryCode;
}
