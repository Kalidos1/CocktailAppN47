package com.hackdays.cocktailapp.north47.domain;


import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddToFavouriteCriteria {
    @NotNull
    private String drinkId;
    @NotNull
    private String barId;
}
