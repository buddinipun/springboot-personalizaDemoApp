package com.smtaps.demoApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smtaps.demoApi.entity.Product;
import com.smtaps.demoApi.service.ProductService;
@RestController
@RequestMapping("/api/v1/external")
public class ExternalServicesController {
	
	@Autowired
    private ProductService productService;
	
	@GetMapping("/getproducts")
    @PreAuthorize("hasRole('client_external')")
    public ResponseEntity<List<Product>> getProductsByShopperWithFilters(
            @RequestParam String shopperId,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false, defaultValue = "10") int limit) {
        List<Product> products = productService.getProductsByShopperWithFilters(shopperId, category, brand, limit);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

}
