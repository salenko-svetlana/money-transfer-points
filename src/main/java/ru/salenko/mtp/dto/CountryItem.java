package ru.salenko.mtp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Информация о стране
 */
@Getter
@AllArgsConstructor
public class CountryItem {
    private String code;
    private String name;
}
