package com.hackdays.cocktailapp.north47.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "DrinkDetails")
@Table
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DrinkDetails {
    @Id
    private String idDrink;
    @ElementCollection(targetClass = String.class)
    private List<String> strIngredients = new ArrayList<>();
    @ElementCollection(targetClass = String.class)
    private List<String> strMeasures = new ArrayList<>();
}
