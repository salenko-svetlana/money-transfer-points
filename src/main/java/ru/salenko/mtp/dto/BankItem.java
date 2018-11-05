package ru.salenko.mtp.dto;

import lombok.Getter;

@Getter
public class BankItem {
    /**
     * Код банка
     */
    private String code;

    /**
     * Название банка
     */
    private String name;

    public BankItem(String code, String name){
        this.code = code;
        this.name = name;
    }
}



