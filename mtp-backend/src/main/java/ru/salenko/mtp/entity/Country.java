package ru.salenko.mtp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Country {

    @Id
    @GeneratedValue
    private Long id;

    private String code;
    private String name;
    private String description;

}
