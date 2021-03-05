package com.example.spring.belajar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.example.spring.belajar.model.Product;

import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private List<Product> products = new ArrayList<>();

    public ProductService() {
        products.add(new Product(1, "Product1", 10000, "Ini product1"));
        products.add(new Product(2, "Product2", 20000, "Ini product2"));
        products.add(new Product(3, "Product3", 30000, "Ini product3"));
    }

    public List<Product> getAllProducts() {
        return this.products;
    }

    public Product getProduct(int index) {
        return this.products.get(index);
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public Product getProduct(String name) {
        Optional<Product> result = null;

        try{
            result = this.products.stream().filter(p -> p.getName().equalsIgnoreCase(name)).findFirst();
            System.out.println(result.get().getName());
        } catch(NoSuchElementException e) {
            System.err.println(name + " not found!");
            return null;
        }
        
        return result.get();
        
    }
}
