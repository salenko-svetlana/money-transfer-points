package ru.salenko.mtp;

import javax.persistence.*;

@Entity
public class Point {

    @Id
    @GeneratedValue
    public Long id;

    public String code;
    public String name;
    public String description;

    @ManyToOne(targetEntity = Bank.class)
    @JoinColumn(name = "BANK", referencedColumnName = "ID")
    private Bank bank;

    @ManyToOne(targetEntity = City.class)
    @JoinColumn(name = "CITY", referencedColumnName = "ID")
    private City city;
}
