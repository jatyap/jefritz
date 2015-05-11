package com.jatyap.jefritz.dao;

import java.util.Date;
import java.util.List;

import com.jatyap.jefritz.model.ProductPricing;

public interface ProductPricingDAO {
	public List<ProductPricing> listProductPricing(Date referenceDate);
	public void savePricing(ProductPricing pricing);
	public ProductPricing getPricing(String productId, Date referenceDate);
}
