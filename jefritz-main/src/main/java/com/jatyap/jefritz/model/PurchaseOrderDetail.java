package com.jatyap.jefritz.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="purchaseorderdetail")
public class PurchaseOrderDetail extends OrderDetail {
	
//	@ManyToOne(targetEntity=PurchaseOrder.class)
//	@JoinColumn(name="orderid")
//	protected PurchaseOrder order;
//
//	//@Override
//	public PurchaseOrder getOrder() {
//		return (PurchaseOrder)this.order;
//	}
//
//	//@Override
//	public <T extends Order> void setOrder(T order){
//		if(!(order instanceof PurchaseOrder)){
//			throw new IllegalArgumentException();
//		}
//		this.order = (PurchaseOrder)order;
//	}

}
