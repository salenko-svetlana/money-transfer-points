package ru.salenko.mtp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Банк, являющийся владельцем пунктов выдачи/отправки переводов в указанном городе
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Bank {
    @Id
    @GeneratedValue
    private Long id;

    public Bank(String code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * Код банка
     */
    private String code;

    /**
     * Название банка
     */
    private String name;

    /**
     * Описание банка
     */
    private String description;
}
