package com.jatyap.jefritz.entity;

import java.math.BigDecimal;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class OrderDetail {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected long detailId;
	
	protected BigDecimal quantity;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="product")
	protected Product product;
	
	protected BigDecimal pricePerUnit;
	
	public long getDetailId() {
		return detailId;
	}
	public void setDetailId(long detailId) {
		this.detailId = detailId;
	}
	
//	public abstract <T extends Order> T getOrder();
//	public abstract <T extends Order> void setOrder(T order);
	
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public BigDecimal getPricePerUnit() {
		return pricePerUnit;
	}
	public void setPricePerUnit(BigDecimal pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}
}
