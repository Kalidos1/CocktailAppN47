package com.hackdays.cocktailapp.north47.domain;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class DrinkDTO {
    private String idDrink;
    private String strDrink;
    private String strDrinkThumb;
    private Map<String, DrinkDetailsDTO> bars = new HashMap<>();
}
