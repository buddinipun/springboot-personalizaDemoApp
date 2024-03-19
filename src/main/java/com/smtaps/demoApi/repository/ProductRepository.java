package com.smtaps.demoApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smtaps.demoApi.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	  Product findByProductId(String productId);
}
