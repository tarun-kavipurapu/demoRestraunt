package com.example.demo.controller;

import com.example.demo.model.Restaurant;
import com.example.demo.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/test")
public class ProductController {

    @Autowired
    private RestaurantService productService;
    @GetMapping("/all")
    public List<Restaurant> getProducts() {
        return productService.getProducts();
    }
    @PostMapping("/insert")
    public Restaurant insert(@RequestBody Restaurant restaurant){
        return  productService.addProduct(restaurant);
    }
    @GetMapping("/endpoint")
    public String handleRequest() {
        String response = "hello world";
        return response;
    }

}
