package com.jatyap.jefritz.dao;

import com.jatyap.jefritz.model.ProductPricingType;


public class PurchaseProductPricingDAOImpl extends BaseProductPricingDAOImpl {

	public PurchaseProductPricingDAOImpl(){
		this.setPricingType(ProductPricingType.PURCHASE);
	}
}
