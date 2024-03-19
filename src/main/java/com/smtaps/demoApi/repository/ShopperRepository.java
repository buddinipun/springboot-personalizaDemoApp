package com.smtaps.demoApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smtaps.demoApi.entity.Shopper;

public interface ShopperRepository extends JpaRepository<Shopper, Long> {
}
