package com.example.spring.belajar.repository;

import com.example.spring.belajar.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
    Product getProductByName(String name);
    Product getProductById(int id);
}
