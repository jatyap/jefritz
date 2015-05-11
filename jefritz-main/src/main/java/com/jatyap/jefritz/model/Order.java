package com.jatyap.jefritz.model;

import java.util.Date;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.format.annotation.DateTimeFormat;

@MappedSuperclass
public abstract class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int orderId;

	@DateTimeFormat (pattern="yyyy-MM-dd")
	protected Date orderDate;
	
	protected Order(){
		this.orderDate = new Date();
	}
	
	public void setOrderId(int orderId){
		this.orderId = orderId;
	}
	public int getOrderId(){
		return this.orderId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	public abstract <E extends OrderDetail> List<E> getOrderDetails();

	public abstract <E extends OrderDetail> void addOrderDetail(E detail);
	
	public abstract <E extends OrderDetail> void setOrderDetails(List<E> orderDetails);
}
