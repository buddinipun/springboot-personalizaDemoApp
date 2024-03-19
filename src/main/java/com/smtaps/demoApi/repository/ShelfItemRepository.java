package com.smtaps.demoApi.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smtaps.demoApi.entity.Product;
import com.smtaps.demoApi.entity.ShelfItem;

public interface ShelfItemRepository extends  JpaRepository<ShelfItem, Long> {
	

	@Query("SELECT si.product FROM ShelfItem si JOIN si.shopper s " +
            "WHERE s.shopperId = :shopperId " +
            "AND (:category IS NULL OR si.product.category = :category) " +
            "AND (:brand IS NULL OR si.product.brand = :brand)")
    List<Product> findProductsByShopperWithFilters(
            @Param("shopperId") String shopperId,
            @Param("category") String category,
            @Param("brand") String brand);

}
