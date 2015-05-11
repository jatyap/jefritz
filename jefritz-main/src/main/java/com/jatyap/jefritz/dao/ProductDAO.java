package com.jatyap.jefritz.dao;

import java.util.List;

import com.jatyap.jefritz.entity.Product;

public interface ProductDAO {

	public List<Product> listProducts();
	public void saveProduct(Product product);
	public Product getProduct(String productId);
	public void deleteProduct(String productId);
}
