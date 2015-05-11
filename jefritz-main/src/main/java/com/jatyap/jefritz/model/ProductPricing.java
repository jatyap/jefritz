package com.jatyap.jefritz.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class ProductPricing {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected int pricingId;

	@ManyToOne(optional = false)
	@JoinColumn(name = "productid", nullable = false, updatable = false)
	protected Product product;

	protected Date validFrom = new Date();

	protected Date validTo = null;

	protected BigDecimal price;
	
	public ProductPricing(){
		this.product = new Product();
	}

	public int getPricingId() {
		return pricingId;
	}

	public void setPricingId(int pricingId) {
		this.pricingId = pricingId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
