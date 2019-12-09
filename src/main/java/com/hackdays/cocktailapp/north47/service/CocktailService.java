package com.hackdays.cocktailapp.north47.service;

import com.hackdays.cocktailapp.north47.domain.AddToFavouriteCriteria;
import com.hackdays.cocktailapp.north47.domain.Bar;
import com.hackdays.cocktailapp.north47.domain.Drink;
import com.hackdays.cocktailapp.north47.domain.DrinkDTO;
import com.hackdays.cocktailapp.north47.domain.DrinkDetailsDTO;
import com.hackdays.cocktailapp.north47.domain.Favourite;
import com.hackdays.cocktailapp.north47.domain.Order;
import com.hackdays.cocktailapp.north47.domain.User;
import com.hackdays.cocktailapp.north47.repo.DrinkRepository;
import com.hackdays.cocktailapp.north47.repo.FavouriteRepository;
import com.hackdays.cocktailapp.north47.repo.OrderRepository;
import com.hackdays.cocktailapp.north47.repo.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class CocktailService {

    @Autowired
    private DrinkRepository drinkRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private FavouriteRepository favouriteRepository;


    public List<DrinkDTO> getListOfDrinks(String partnerId) {
        Optional<User> userOptional = userRepository.findById(partnerId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Set<Favourite> favourites = favouriteRepository.findAllByUserId(partnerId);
            List<Drink> drinks = drinkRepository.findAll();
            return drinks.stream().map(drink -> mapToDrinkDTO(drink, favourites)).collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }


    }

    private DrinkDTO mapToDrinkDTO(Drink drink, Set<Favourite> favourites) {
        DrinkDTO drinkDTO = new DrinkDTO();
        drinkDTO.setIdDrink(drink.getIdDrink());
        drinkDTO.setStrDrink(drink.getStrDrink());
        drinkDTO.setStrDrinkThumb(drink.getStrDrinkThumb());
        Map<String, DrinkDetailsDTO> bars = drink.getBars().stream().collect(Collectors.toMap(Bar::getId, bar -> {
            DrinkDetailsDTO drinkDetailsDTO = new DrinkDetailsDTO();
            drinkDetailsDTO.setBarName(bar.getName());
            drinkDetailsDTO.setFavourite(favourites.stream().anyMatch(favourite -> bar.getId().equals(favourite.getBarId()) && drink.getIdDrink().equals(favourite.getDrinkId())));
            drinkDetailsDTO.setPrice(ThreadLocalRandom.current().nextDouble(5, 11));
            return drinkDetailsDTO;
        }));
        drinkDTO.setBars(bars);
        return drinkDTO;
    }


    public User addToFavourites(String userId, AddToFavouriteCriteria criteria) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Favourite favourite = new Favourite();
            favourite.setBarId(criteria.getBarId());
            favourite.setDrinkId(criteria.getDrinkId());
            favourite.setUserId(userId);
            if (!user.getFavourites().contains(favourite)) {
                user.getFavourites().add(favourite);
                return userRepository.save(user);
            }
            return null;
        }
        return null;
    }


    public User createNewOrder(String partnerId, AddToFavouriteCriteria criteria) {
        Optional<User> userOptional = userRepository.findById(partnerId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Order order = new Order();
            //   order.getDrinks().addAll(criteria.getFavourites());
            user.getOrders().add(order);
            return userRepository.save(user);

        }
        return null;
    }
}
