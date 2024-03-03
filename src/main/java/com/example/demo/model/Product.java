package com.example.demo.model;
//
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
//

/**
 * User Model
 * */
@Document(collection ="products")
public class Product {

    private String name ;
    private double price;
    private Integer quantity;



    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product(String name, double price, Integer quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}
