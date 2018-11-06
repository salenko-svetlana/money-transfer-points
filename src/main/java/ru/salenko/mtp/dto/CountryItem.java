package ru.salenko.mtp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Информация о стране
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CountryItem {
    private String code;
    private String name;
}
