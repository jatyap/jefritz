package com.jatyap.jefritz;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.jatyap.jefritz.dao.ProductDAO;
import com.jatyap.jefritz.entity.Product;

public class ProductTester {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		ProductDAO productDAO = context.getBean(ProductDAO.class);

//		Product product = new Product();
//		product.setProductId("SIRLOINXG");
//		product.setProductName("Ground Pork XG");
//
//		productDAO.saveProduct(product);

		List<Product> productList = productDAO.listProducts();
		for (Product prod : productList) {
			System.out.println(prod.getProductId() + " | " + prod.getProductName());
		}
		
		System.out.println(productDAO.getProduct("MAYTALI").toString());

		context.close();
	}

}
