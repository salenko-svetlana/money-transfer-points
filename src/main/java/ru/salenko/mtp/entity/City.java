package ru.salenko.mtp.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class City {
    @Id
    @GeneratedValue
    private Long id;

    private String code;
    private String name;
    private String description;

    @ManyToOne(targetEntity = Country.class)
    @JoinColumn(name = "COUNTRY", referencedColumnName = "ID")
    private Country country;
}
