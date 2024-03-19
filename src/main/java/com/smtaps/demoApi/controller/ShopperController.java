package com.smtaps.demoApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smtaps.demoApi.DTO.ShopperDTO;
import com.smtaps.demoApi.entity.Shopper;
import com.smtaps.demoApi.service.ShopperService;

@RestController
@RequestMapping("/api/v1/internal")
public class ShopperController {

    @Autowired
    private ShopperService shopperService;

    @PostMapping("/shoppers")
    @PreAuthorize("hasRole('client_internal')")
    public ResponseEntity<String> saveShopperDetails(@RequestBody ShopperDTO shopperDTO) {
        try {
            shopperService.saveShopperDetails(shopperDTO);
            return new ResponseEntity<>("Shopper details saved successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
