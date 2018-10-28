package ru.salenko.mtp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Country {

    @Id
    @GeneratedValue
    private Long id;

    private String code;
    private String name;
    private String description;

    public Country() {
    }

    public Country(String code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }

}
