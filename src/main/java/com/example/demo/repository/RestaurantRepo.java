package com.example.demo.repository;

import com.example.demo.model.Restraunt;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends MongoRepository<Restraunt,Integer> {

}
