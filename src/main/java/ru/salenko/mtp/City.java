package ru.salenko.mtp;

import javax.persistence.*;

@Entity
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
