package com.hackdays.cocktailapp.north47;


import com.hackdays.cocktailapp.north47.domain.AddToFavouriteCriteria;
import com.hackdays.cocktailapp.north47.domain.DrinkDTO;
import com.hackdays.cocktailapp.north47.domain.User;
import com.hackdays.cocktailapp.north47.service.CocktailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@RequestMapping(value = "/cocktails", produces = {MediaType.APPLICATION_JSON_VALUE})
@Slf4j
public class CocktailsController {

    @Autowired
    private CocktailService cocktailService;

    @GetMapping("/{partnerId}")
    public List<DrinkDTO> getListOfDrinks(@PathVariable("partnerId") String partnerId) {
        return cocktailService.getListOfDrinks(partnerId);
    }


    @PostMapping("/{userId}/favourites")
    public User addToFavourites(@PathVariable("userId") String userId,
                                @RequestBody @Valid AddToFavouriteCriteria criteria) {
        return cocktailService.addToFavourites(userId, criteria);
    }

    @PostMapping("/{userId}/order")
    public User createNewOrder(@PathVariable("userId") String userId,
                               @RequestBody @Valid AddToFavouriteCriteria criteria) {
        return cocktailService.createNewOrder(userId, criteria);
    }
}
