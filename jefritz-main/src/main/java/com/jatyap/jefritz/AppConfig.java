package com.jatyap.jefritz;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.FormHttpMessageConverter;

import com.jatyap.jefritz.dao.OrderDAO;
import com.jatyap.jefritz.dao.OrderDAOImpl;
import com.jatyap.jefritz.dao.ProductDAO;
import com.jatyap.jefritz.dao.ProductDAOImpl;
import com.jatyap.jefritz.dao.ProductPricingDAO;
import com.jatyap.jefritz.dao.PurchaseProductPricingDAOImpl;
import com.jatyap.jefritz.dao.SalesOrderDAO;
import com.jatyap.jefritz.dao.SalesOrderDAOImpl;

@Configuration
public class AppConfig {

   
	@Bean
	public ProductDAO getProductDAO() {
		return new ProductDAOImpl();
	}

	@Bean
	public ProductPricingDAO getPurchasePricingDAO() {
		return new PurchaseProductPricingDAOImpl();
	}

	@Bean
	public OrderDAO getPurchaseOrderDAO() {
		return new OrderDAOImpl();
	}

	@Bean
	public SalesOrderDAO getSalesOrderDAO() {
		return new SalesOrderDAOImpl();
	}

}
