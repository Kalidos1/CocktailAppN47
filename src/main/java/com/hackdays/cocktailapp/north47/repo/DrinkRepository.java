package com.hackdays.cocktailapp.north47.repo;

import com.hackdays.cocktailapp.north47.domain.Drink;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface DrinkRepository extends JpaRepository<Drink, String> {

}
