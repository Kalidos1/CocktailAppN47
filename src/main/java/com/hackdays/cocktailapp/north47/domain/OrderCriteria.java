package com.hackdays.cocktailapp.north47.domain;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class OrderCriteria {
    @NotNull
    private List<String> drinks;
}
