package com.smtaps.demoApi.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smtaps.demoApi.DTO.ShelfItemDTO;
import com.smtaps.demoApi.DTO.ShopperDTO;
import com.smtaps.demoApi.entity.Product;
import com.smtaps.demoApi.entity.ShelfItem;
import com.smtaps.demoApi.entity.Shopper;
import com.smtaps.demoApi.repository.ProductRepository;
import com.smtaps.demoApi.repository.ShelfItemRepository;
import com.smtaps.demoApi.repository.ShopperRepository;
import com.smtaps.demoApi.service.ShopperService;

import jakarta.transaction.Transactional;

@Service
public class ShopperServiceImpl implements ShopperService {

    @Autowired
    private ShopperRepository shopperRepository;

    @Autowired
    private ShelfItemRepository shelfItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    @Override
    public void saveShopperDetails(ShopperDTO shopperDTO) {
        Shopper shopper = new Shopper();
        shopper.setShopperId(shopperDTO.getShopperId());
        shopperRepository.save(shopper);
        for (ShelfItemDTO shelfItemDTO : shopperDTO.getShelf()) {
            Product product = productRepository.findByProductId(shelfItemDTO.getProductId());

            if (product != null) {
                ShelfItem shelfItem = new ShelfItem();
                shelfItem.setProduct(product);
                shelfItem.setRelevancyScore(shelfItemDTO.getRelevancyScore());
                shelfItem.setShopper(shopper);
                shelfItemRepository.save(shelfItem);
            }
        }

       
    }

	@Override
	public Shopper saveShopper(Shopper shopper) {
		// TODO Auto-generated method stub
		return null;
	}
    
}

