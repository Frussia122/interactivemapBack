package com.example.apiWithDb.repository;

import com.example.apiWithDb.entities.Favourites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FavouritesRepository extends JpaRepository<Favourites, String> {
    List<Favourites> findAllFavouritesByUserId(Long id);
    Favourites findByIdAndUserId(Long id, Long userId);
}

