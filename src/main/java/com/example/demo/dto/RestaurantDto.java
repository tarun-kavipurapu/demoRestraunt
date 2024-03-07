package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantDto {
    private String restaurantName;
    private String cuisine;
    private Integer rating;
    private String address;
    private String phoneNumber;
    @Email
    private String email;
    private String website;
}
