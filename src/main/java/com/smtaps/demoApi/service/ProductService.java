package com.smtaps.demoApi.service;

import java.util.List;

import com.smtaps.demoApi.entity.Product;

public interface ProductService {
	
	Product saveProduct(Product product);
	
	public List<Product> getProductsByShopperWithFilters(String shopperId, String category, String brand, int limit);

}
