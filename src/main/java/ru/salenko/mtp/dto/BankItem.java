package ru.salenko.mtp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Информация о банке
 */
@Getter
@Setter
@AllArgsConstructor
public class BankItem {
    /**
     * Код банка
     */
    private String code;

    /**
     * Название банка
     */
    private String name;
}



