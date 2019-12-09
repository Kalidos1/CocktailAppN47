package com.hackdays.cocktailapp.north47.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "Drink")
@Table
@Getter
@Setter
public class Drink {

    @Id
    private String idDrink;
    private String strDrink;
    private String strDrinkThumb;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "drinks")
    private Set<Bar> bars = new HashSet<>();

}
