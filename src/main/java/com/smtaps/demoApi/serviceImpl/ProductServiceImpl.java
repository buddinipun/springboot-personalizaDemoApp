package com.smtaps.demoApi.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smtaps.demoApi.entity.Product;
import com.smtaps.demoApi.repository.ProductRepository;
import com.smtaps.demoApi.repository.ShelfItemRepository;
import com.smtaps.demoApi.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private ShelfItemRepository shelfItemRepository;

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
    
    
    @Override
    public List<Product> getProductsByShopperWithFilters(String shopperId, String category, String brand, int limit) {
        List<Product> products = shelfItemRepository.findProductsByShopperWithFilters(shopperId, category, brand);
        return products.stream().limit(limit).collect(Collectors.toList());
    }
}
