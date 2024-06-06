package com.example.apiWithDb.controller;

import com.example.apiWithDb.config.UserAuthProvider;
import com.example.apiWithDb.entities.Favourites;
import com.example.apiWithDb.entities.User;
import com.example.apiWithDb.repository.FavouritesRepository;
import com.example.apiWithDb.response.ResponseHandler;
import com.example.apiWithDb.service.FavouritesService;
import com.example.apiWithDb.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.AuthProvider;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/favourites")
@SecurityRequirement(name = "bearerAuth")
public class FavouritesController {

    FavouritesService favouritesService;
    UserService userService;
    FavouritesRepository favouritesRepository;

    public FavouritesController(FavouritesService favouritesService, UserService userService, FavouritesRepository favouritesRepository) {
        this.favouritesService = favouritesService;
        this.userService = userService;
        this.favouritesRepository = favouritesRepository;
    }

    @GetMapping
    public List<Favourites> getAllFavourites(Authentication authentication) {
        User user = userService.findUserByToken(authentication);
        return favouritesRepository.findAllFavouritesByUserId(user.getId());
    }

    @GetMapping("/{favouriteId}")
    public Favourites getFavourite(@PathVariable("favouriteId") Long favouriteId, Authentication authentication) {
        User user = userService.findUserByToken(authentication);
        return favouritesRepository.findByIdAndUserId(favouriteId,user.getId());
    }

    @PostMapping
    public  String createFavourite(@RequestBody Favourites favourites, Authentication authentication) {
        User user = userService.findUserByToken(authentication);
        favourites.setUser(user);
        favouritesService.createFavourite(favourites);
        return "asdasd";
    }

    @PatchMapping("/{favouriteId}")
    public Favourites updateFavourite(@PathVariable("favouriteId") String favouriteId, Map<String, Object> fields ) {
        return favouritesService.updateFavourite(favouriteId, fields);
    }

    @DeleteMapping("/{favouriteId}")
    public  String deleteFavourite(@PathVariable("favouriteId") String favouriteId) {
        return favouritesService.deleteFavourite(favouriteId);
    }
}
