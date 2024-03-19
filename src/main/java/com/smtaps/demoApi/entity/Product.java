package com.smtaps.demoApi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "product_id", nullable = false, unique = true)
    private String productId;
    
    @Column(nullable = false)
    private String category;
    
    @Column(nullable = false)
    private String brand;
    
    // Constructors, getters, setters
}
