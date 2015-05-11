package com.jatyap.jefritz.model;

public enum OrderType {
	SALES ("SalesOrder"),
	PURCHASE ("PurchaseOrder");
	
	private String value;
	
	private OrderType(String value){
		this.value = value;
	}
	
	public String toString(){
		return this.value;
	}
}