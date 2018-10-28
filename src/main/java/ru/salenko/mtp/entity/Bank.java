package ru.salenko.mtp.entity;

import javax.persistence.*;

@Entity
public class Bank {

    @Id
    @GeneratedValue
    public Long id;

    public String code;
    public String name;
    public String description;

    @ManyToOne(targetEntity = City.class)
    @JoinColumn(name = "CITY", referencedColumnName = "ID")
    public City city;
}
