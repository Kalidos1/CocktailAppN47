package com.hackdays.cocktailapp.north47;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@RequestMapping(value = "/cooktails", produces = {MediaType.APPLICATION_JSON_VALUE})
@Slf4j
public class CocktailsController {
}
