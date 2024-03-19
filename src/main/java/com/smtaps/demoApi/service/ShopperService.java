package com.smtaps.demoApi.service;

import com.smtaps.demoApi.DTO.ShopperDTO;
import com.smtaps.demoApi.entity.Shopper;

public interface ShopperService {
	
	  Shopper saveShopper(Shopper shopper);
	  
	  public void saveShopperDetails(ShopperDTO shopperDTO);

}
