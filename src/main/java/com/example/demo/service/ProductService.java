package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Component
@Service
public class ProductService {
    @Autowired
    private ProductRepo productrepo;
        public List<Product> getProducts() {
        return productrepo.findAll();
    }

    public Product addProduct(Product product){
        return productrepo.save(product);
    }
}
