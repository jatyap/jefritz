package com.jatyap.jefritz.dao;

import java.util.List;

import com.jatyap.jefritz.model.Product;

public interface ProductDAO {

	public void saveProduct(Product product);
	public List<Product> listProducts();
	public Product getProduct(String productId);
	public void deleteProduct(String productId);
}
