package ru.salenko.mtp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
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
    public Long id;

    public Bank(String code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * Код банка
     */
    public String code;

    /**
     * Название банка
     */
    public String name;

    /**
     * Описание банка
     */
    public String description;
}
