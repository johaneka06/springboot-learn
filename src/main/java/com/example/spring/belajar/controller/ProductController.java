package com.example.spring.belajar.controller;

import java.util.List;

import com.example.spring.belajar.model.Product;
import com.example.spring.belajar.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepo;

    @GetMapping("")
    public ResponseEntity<List<Product>> getAllProducts() {
        return (productRepo.findAll().size() != 0) ? new ResponseEntity<>(productRepo.findAll(), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/detail")
    public ResponseEntity<Product> getProduct(@RequestParam int id) {
        Product product = productRepo.getProductById(id);
        return (product != null) ? new ResponseEntity<>(product, HttpStatus.OK) : new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/find/{identifier}")
    public ResponseEntity<Product> getProductByName(@PathVariable String identifier) {
        Product product = productRepo.getProductByName(identifier);
        return (product != null) ? new ResponseEntity<>(product, HttpStatus.OK) : new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("")
    public ResponseEntity<Product> addProduct(@RequestBody Product p) {
        productRepo.save(p);
        p = productRepo.getProductByName(p.getName());
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @DeleteMapping("/product/{id}/delete")
    public ResponseEntity<Product> updateProduct(@PathVariable int id) {

        if (productRepo.getProductById(id) !=  null){
            productRepo.delete(productRepo.getProductById(id));
            return new ResponseEntity<>(HttpStatus.OK);
        }
    
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
