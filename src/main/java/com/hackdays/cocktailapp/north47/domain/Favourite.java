package com.hackdays.cocktailapp.north47.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "Favourite")
@Table
@Getter
@Setter
@ToString
@IdClass(Favourite.FavouriteId.class)
public class Favourite implements Serializable {

    @Id
    private String userId;
    @Id
    private String drinkId;
    @Id
    private String barId;

    @NoArgsConstructor
    @AllArgsConstructor
    public static class FavouriteId implements Serializable {
        private String userId;
        private String drinkId;
        private String barId;
    }
}
