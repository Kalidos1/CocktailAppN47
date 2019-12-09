package com.hackdays.cocktailapp.north47.domain;

import lombok.Data;

@Data
public class DrinkDetailsDTO {
    private String barName;
    private boolean favourite;
    private double price;
}
