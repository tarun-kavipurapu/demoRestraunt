package com.example.demo.model;
//
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
//

/**
 * User Model
 * */
@Document(collection ="restaurant")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {
    @Id
    private String id;
    private String restaurantName;
    private String cuisine;
    private Integer rating;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String phoneNumber;
    private String email;
    private String website;

    public Restaurant(String restaurantName, String cuisine, Integer rating, String address,  String phoneNumber, String email, String website) {
        this.restaurantName = restaurantName;
        this.cuisine = cuisine;
        this.rating = rating;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.website = website;
    }
}
