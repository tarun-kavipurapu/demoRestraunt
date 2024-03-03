package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RestController
@RequestMapping
public class ProductController {

    @Autowired
    private ProductService productService;
    @GetMapping("/all")
    public List<Product> getProducts() {
        return productService.getProducts();
    }
    @PostMapping("/insert")
    public Product insert(@RequestBody Product product){
        return  productService.addProduct(product);
    }
    @GetMapping("/endpoint")
    public String handleRequest() {
        String response = "hello world";
        return response;
    }

}
