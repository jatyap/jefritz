package com.jatyap.jefritz.dao;

import com.jatyap.jefritz.entity.ProductPricingType;


public class PurchaseProductPricingDAOImpl extends BaseProductPricingDAOImpl {

	public PurchaseProductPricingDAOImpl(){
		this.setPricingType(ProductPricingType.PURCHASE);
	}
}
