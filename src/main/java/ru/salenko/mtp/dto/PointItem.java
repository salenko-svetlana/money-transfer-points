package ru.salenko.mtp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Информация о пункте получения/отправки денежных переводов
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PointItem {
    private String code;
    private String name;
    private String cityCode;
}