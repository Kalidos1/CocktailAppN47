package com.hackdays.cocktailapp.north47.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackdays.cocktailapp.north47.domain.Bar;
import com.hackdays.cocktailapp.north47.domain.Drink;
import com.hackdays.cocktailapp.north47.domain.DrinkDetails;
import com.hackdays.cocktailapp.north47.domain.User;
import com.hackdays.cocktailapp.north47.repo.BarRepository;
import com.hackdays.cocktailapp.north47.repo.DrinkDetailsRepository;
import com.hackdays.cocktailapp.north47.repo.DrinkRepository;
import com.hackdays.cocktailapp.north47.repo.UserRepository;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
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

    @Autowired
    private DrinkDetailsRepository drinkDetailsRepository;

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
            //List<DrinkDetails> drinkDetailsList = new ArrayList<>();
            list.stream().map(Drink::getIdDrink).forEach(id -> {
                try {
                    Map<String, Object> props = getDrinkDetails(id);
                    DrinkDetails drinkDetails = new DrinkDetails();
                    drinkDetails.setIdDrink(id);
                    if (props.containsKey("strIngredient1") && props.get("strIngredient1") != null) {
                        drinkDetails.getStrIngredients().add(props.get("strIngredient1").toString());
                    }
                    if (props.containsKey("strIngredient2") && props.get("strIngredient2") != null) {
                        drinkDetails.getStrIngredients().add(props.get("strIngredient2").toString());
                    }
                    if (props.containsKey("strIngredient3") && props.get("strIngredient3") != null) {
                        drinkDetails.getStrIngredients().add(props.get("strIngredient1").toString());
                    }
                    if (props.containsKey("strIngredient4") && props.get("strIngredient4") != null) {
                        drinkDetails.getStrIngredients().add(props.get("strIngredient4").toString());
                    }
                    if (props.containsKey("strIngredient5") && props.get("strIngredient5") != null) {
                        drinkDetails.getStrIngredients().add(props.get("strIngredient5").toString());
                    }

                    if (props.containsKey("strMeasure1") && props.get("strMeasure1") != null) {
                        drinkDetails.getStrMeasures().add(props.get("strMeasure1").toString());
                    }
                    if (props.containsKey("strMeasure2") && props.get("strMeasure2") != null) {
                        drinkDetails.getStrMeasures().add(props.get("strMeasure2").toString());
                    }
                    if (props.containsKey("strMeasure3") && props.get("strMeasure3") != null) {
                        drinkDetails.getStrMeasures().add(props.get("strMeasure3").toString());
                    }
                    if (props.containsKey("strMeasure4") && props.get("strMeasure4") != null) {
                        drinkDetails.getStrMeasures().add(props.get("strMeasure4").toString());
                    }
                    if (props.containsKey("strMeasure5") && props.get("strMeasure5") != null) {
                        drinkDetails.getStrMeasures().add(props.get("strMeasure5").toString());
                    }
                    drinkDetailsRepository.save(drinkDetails);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        List<User> users = new ArrayList<>();
        IntStream.range(0, 5).forEach(index -> users.add(new User()));
        userRepository.saveAll(users);

    }


    private Map<String, Object> getDrinkDetails(String id) throws IOException {
        String sURL = String.format("https://www.thecocktaildb.com/api/json/v1/1/lookup.php?i=%s", id); //just a string
        JSONObject json = new JSONObject(IOUtils.toString(new URL(sURL), Charset.forName("UTF-8")));
        JSONArray arr = json.getJSONArray("drinks");
        return ((JSONObject) arr.get(0)).toMap();
    }


}


