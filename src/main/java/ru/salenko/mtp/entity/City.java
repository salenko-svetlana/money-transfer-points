package ru.salenko.mtp.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class City {

    @Id
    @GeneratedValue
    public Long id;

    public String code;
    public String name;
    public String description;

    @ManyToOne(targetEntity = Country.class)
    @JoinColumn(name = "COUNTRY", referencedColumnName = "ID")
    private Country country;
}
