package com.example.countries_data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "countries")
@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String capital;
    private double area;
    private Long population;
    private double gdp;
    private String currency;

    public Country() {
    }

    public Country(Long id, String name, String capital, double area, Long population, double gdp, String currency) {
        this.id = id;
        this.name = name;
        this.capital = capital;
        this.area = area;
        this.population = population;
        this.gdp = gdp;
        this.currency = currency;
    }
}
