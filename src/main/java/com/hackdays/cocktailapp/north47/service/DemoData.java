package com.hackdays.cocktailapp.north47.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackdays.cocktailapp.north47.domain.Bar;
import com.hackdays.cocktailapp.north47.domain.Drink;
import com.hackdays.cocktailapp.north47.domain.User;
import com.hackdays.cocktailapp.north47.repo.BarRepository;
import com.hackdays.cocktailapp.north47.repo.DrinkRepository;
import com.hackdays.cocktailapp.north47.repo.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class DemoData implements CommandLineRunner {

    @Autowired
    private DrinkRepository drinkRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BarRepository barRepository;

    @Override
    public void run(String... args) throws JsonProcessingException {
        try (InputStream inputStream = getClass().getResourceAsStream("/drinks.json")) {
            ObjectMapper mapper = new ObjectMapper(); // just need one
            List<Drink> list = mapper.readValue(inputStream, new TypeReference<List<Drink>>() {
            });
            list.forEach(drink -> {
                drinkRepository.save(drink);
            });
            log.info("done storing content.");
            Bar bar1 = new Bar();
            bar1.setId("bar1");
            bar1.setName("Zu mir oder zu dir");
            bar1.getDrinks().addAll(list.subList(0, 65));
            Bar bar2 = new Bar();
            bar2.setId("bar2");
            bar2.setName("klimperkasten");
            bar2.getDrinks().addAll(list.subList(33, 99));
            barRepository.saveAll(Arrays.asList(bar1, bar2));
            log.info("shit");


        } catch (Exception e) {
            e.printStackTrace();
        }
        List<User> users = new ArrayList<>();
        IntStream.range(0, 5).forEach(index -> users.add(new User()));
        userRepository.saveAll(users);


    }


}


