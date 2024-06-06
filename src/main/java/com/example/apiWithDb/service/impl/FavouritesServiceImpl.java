package com.example.apiWithDb.service.impl;

import com.example.apiWithDb.entities.Favourites;
import com.example.apiWithDb.exception.AppException;
import com.example.apiWithDb.repository.FavouritesRepository;
import com.example.apiWithDb.service.FavouritesService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class FavouritesServiceImpl implements FavouritesService {

    FavouritesRepository favouritesRepository;

    public FavouritesServiceImpl(FavouritesRepository favouritesRepository) {
        this.favouritesRepository = favouritesRepository;
    }

    @Override
    public List<Favourites> getAllFavourites() {
        return favouritesRepository.findAll();
    }

    @Override
    public Favourites getFavourite(String Id) {
        if(favouritesRepository.findById(Id).isEmpty())
            throw  new AppException("Favourite not found", HttpStatus.NOT_FOUND);
        return favouritesRepository.findById(Id).get();
    }

    @Override
    public String deleteFavourite(String Id) {
        if(favouritesRepository.findById(Id).isEmpty())
            throw  new AppException("Favourite not found", HttpStatus.NOT_FOUND);
       favouritesRepository.deleteById(Id);
       return "Favourite was deleted success";
    }

    @Override
    public Favourites updateFavourite(String Id, Map<String, Object> fields) {
        Optional<Favourites> favourite = favouritesRepository.findById(Id);

        if(favourite.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Favourites.class, key);
                field.setAccessible(true);
                org.springframework.util.ReflectionUtils.setField(field, favourite.get(), value);
            });
            return favouritesRepository.save(favourite.get());
        }
        return null;
    }

    @Override
    public String createFavourite(Favourites favourite) {

        favouritesRepository.save(favourite);
        return "Favourite was created";
    }
}
