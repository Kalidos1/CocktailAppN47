package com.hackdays.cocktailapp.north47.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "Drink")
@Table
@Getter
@Setter
public class Drink {
    @Id
    private String strDrink;
    private String strDrinkThumb;
    private String idDrink;
}
