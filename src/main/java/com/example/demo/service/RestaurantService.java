package com.example.demo.service;

import com.example.demo.dto.RestaurantDto;
import com.example.demo.model.Restaurant;
import com.example.demo.repository.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepo restaurantRepo;

    @Autowired

    public List<Restaurant> getRestaurant() {
        return restaurantRepo.findAll();
    }

    public Restaurant addRestaurant(RestaurantDto restaurant) {

        return restaurantRepo.save(new Restaurant(restaurant.getRestaurantName(), restaurant.getCuisine(), restaurant.getRating(), restaurant.getAddress(), restaurant.getPhoneNumber(), restaurant.getEmail(), restaurant.getWebsite()));
    }

    public void deleteRestaurant(String restaurantId) {
        restaurantRepo.deleteById(restaurantId);
    }

    public Restaurant updateRestraunt(String restaurantId, RestaurantDto updateRestaurantDto) {
        /*
        * Instead of using multiple ifs use map method
        * */
        Optional<Restaurant> restaurantOptional = restaurantRepo.findById(restaurantId);
        if (restaurantOptional.isPresent()) {
            Restaurant restaurant = restaurantOptional.get();
            if (updateRestaurantDto.getRestaurantName() != null) {
                restaurant.setRestaurantName(updateRestaurantDto.getRestaurantName());
            }
            if (updateRestaurantDto.getCuisine() != null) {
                restaurant.setCuisine(updateRestaurantDto.getCuisine());
            }
            if (updateRestaurantDto.getRating() != null) {
                restaurant.setRating(updateRestaurantDto.getRating());
            }
            if (updateRestaurantDto.getAddress() != null) {
                restaurant.setAddress(updateRestaurantDto.getAddress());
            }
            if (updateRestaurantDto.getPhoneNumber() != null) {
                restaurant.setPhoneNumber(updateRestaurantDto.getPhoneNumber());
            }
            if (updateRestaurantDto.getEmail() != null) {
                restaurant.setEmail(updateRestaurantDto.getEmail());
            }
            if (updateRestaurantDto.getWebsite() != null) {
                restaurant.setWebsite(updateRestaurantDto.getWebsite());
            }
            return restaurantRepo.save(restaurant);
        } else {
            throw new ResolutionException("Restaurant not found with id " + restaurantId);
        }
    }
}
