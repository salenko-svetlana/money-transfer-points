package ru.salenko.mtp.entity;

import lombok.Getter;
import javax.persistence.*;

@Entity
@Getter
public class Point {

    @Id
    @GeneratedValue
    public Long id;

    private String code;
    private String name;
    private String description;

    @ManyToOne(targetEntity = Bank.class)
    @JoinColumn(name = "BANK", referencedColumnName = "ID")
    private Bank bank;

    @ManyToOne(targetEntity = City.class)
    @JoinColumn(name = "CITY", referencedColumnName = "ID")
    private City city;
}
