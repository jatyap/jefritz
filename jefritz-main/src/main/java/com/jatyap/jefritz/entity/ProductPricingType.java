package com.jatyap.jefritz.entity;

public enum ProductPricingType {
	PURCHASE("PurchaseProductPricing"), SALES("SalesProductPricing");
	
	private String value;
	private ProductPricingType(String value){
		this.value = value;
	}
	
	public String toString(){
		return this.value;
	}
}
