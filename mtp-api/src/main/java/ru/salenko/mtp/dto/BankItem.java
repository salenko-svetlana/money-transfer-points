package ru.salenko.mtp.dto;

import lombok.*;

/**
 * Информация о банке
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BankItem {
    /**
     * Код банка
     */
    private String code;

    /**
     * Название банка
     */
    private String name;

    @Override
    public String toString() {
        return "BankItem{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}



