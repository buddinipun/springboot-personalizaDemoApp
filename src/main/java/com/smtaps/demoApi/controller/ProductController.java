package com.smtaps.demoApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smtaps.demoApi.entity.Product;
import com.smtaps.demoApi.service.ProductService;

@RestController
@RequestMapping("/api/v1/internal")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/products")
    @PreAuthorize("hasRole('client_internal')")
    public Product addProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }
    
}
