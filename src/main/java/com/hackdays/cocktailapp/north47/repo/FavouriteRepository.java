package com.hackdays.cocktailapp.north47.repo;

import com.hackdays.cocktailapp.north47.domain.Favourite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Repository
@Transactional
public interface FavouriteRepository extends JpaRepository<Favourite, Favourite.FavouriteId> {


    Set<Favourite> findAllByUserId(String userId);

}
