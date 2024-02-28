package com.elite.council.controller;

import com.elite.council.entity.Product;
import com.elite.council.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;




    @GetMapping("product/all")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


    @GetMapping("product/{id}")
    public Product getByIdProduct(@PathVariable long id){
        return productRepository.findById(id).orElse(null);
    }

    @PostMapping("/create")
    public Product createProduct(@RequestBody Product product){
        return productRepository.save(product);
    }


    @DeleteMapping("deleteProduct/{id}")
    public void deleteProduct(@PathVariable long id){
        productRepository.deleteById(id);
    }

    @PutMapping("editProduct/{id}")
    public ResponseEntity<Product> editProduct(@PathVariable long id , @RequestBody Product product){
        if(!productRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }else{
           product.setId(id);
           Product editProduct = productRepository.save(product);
           return ResponseEntity.ok(editProduct);
        }
    }
}
