package com.example.demo.repository;

import com.example.demo.model.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepo extends MongoRepository<Restaurant,String> {

}
