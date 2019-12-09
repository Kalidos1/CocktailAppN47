package com.hackdays.cocktailapp.north47.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackdays.cocktailapp.north47.domain.Drink;
import com.hackdays.cocktailapp.north47.repo.DrinkRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class DemoData implements CommandLineRunner {

    @Autowired
    private DrinkRepository drinkRepository;

    @Override
    public void run(String... args) throws JsonProcessingException {
        try (InputStream inputStream = getClass().getResourceAsStream("/drinks.json")) {
            ObjectMapper mapper = new ObjectMapper(); // just need one
            // Or: if no class (and don't need one), just map to Map.class:
            List<Drink> list = mapper.readValue(inputStream, new TypeReference<List<Drink>>() {
            });
            list.forEach(drink -> {
                drinkRepository.save(drink);
            });
            log.info("done storing content.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}


