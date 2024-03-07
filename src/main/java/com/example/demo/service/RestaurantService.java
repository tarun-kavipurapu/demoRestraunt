package com.example.demo.service;

import com.example.demo.model.Restraunt;
import com.example.demo.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Component
@Service
public class RestrauntService {
    @Autowired
    private ProductRepo productrepo;
        public List<Restraunt> getProducts() {
        return productrepo.findAll();
    }

    public Restraunt addProduct(Restraunt restraunt){
        return productrepo.save(restraunt);
    }
}
