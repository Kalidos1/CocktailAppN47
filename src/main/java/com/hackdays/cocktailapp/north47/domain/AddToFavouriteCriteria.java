package com.hackdays.cocktailapp.north47.domain;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class AddToFavouriteCriteria {
    @NonNull
    private String drinkId;
    @NonNull
    private String barId;
}
