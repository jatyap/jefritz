package com.jatyap.jefritz.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "purchaseorder")
public class PurchaseOrder extends Order {

	// @OneToMany(fetch = FetchType.EAGER, mappedBy = "order",
	// cascade=CascadeType.ALL)
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "orderid", referencedColumnName = "orderId")
	private List<PurchaseOrderDetail> orderDetails;

	public PurchaseOrder() {
		super();
		//this.orderDetails = new ArrayList<PurchaseOrderDetail>();
	}

	@Override
	public <E extends OrderDetail> void addOrderDetail(E detail) {
		if (!(detail instanceof PurchaseOrderDetail)) {
			throw new IllegalArgumentException();
		}
		if(null == this.orderDetails){
			this.orderDetails = new ArrayList<PurchaseOrderDetail>();
		}
		// detail.setOrder(this);
		this.orderDetails.add((PurchaseOrderDetail) detail);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PurchaseOrderDetail> getOrderDetails() {
		return this.orderDetails;
	}


	@SuppressWarnings("unchecked")
	@Override
	public <E extends OrderDetail> void setOrderDetails(List<E> orderDetails) {
		this.orderDetails = (List<PurchaseOrderDetail>) orderDetails;
	}
	
	

}
