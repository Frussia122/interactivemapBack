package com.example.apiWithDb.service;

import com.example.apiWithDb.entities.Favourites;

import java.util.List;
import java.util.Map;

public interface FavouritesService {
    public List<Favourites> getAllFavourites();
    public Favourites getFavourite(String Id);
    public String deleteFavourite(String Id);
    public Favourites updateFavourite(String Id, Map<String, Object> fields);
    public String createFavourite(Favourites favourites);
}
