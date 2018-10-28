package ru.salenko.mtp.entity;

import javax.persistence.*;

/**
 * Банк, являющийся владельцем пунктов выдачи/отправки переводов в указанном городе
 */
@Entity
public class Bank {
    @Id
    @GeneratedValue
    public Long id;

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
