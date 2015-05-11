package com.jatyap.jefritz.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jatyap.jefritz.model.Product;

public class ProductDAOImpl extends BaseDAOImpl implements ProductDAO {

	@Override
	public void saveProduct(Product product) {
		Session session = this.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.persist(product);
		tx.commit();
		session.close();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Product> listProducts() {
		Session session = this.getSessionFactory().openSession();
		List<Product> productList = session.createQuery("from Product").list();
		session.close();
		return productList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Product getProduct(String productId) {
		Session session = this.getSessionFactory().openSession();
		Query query = session.createQuery("from Product P where P.productId = :productId");
		query.setString("productId", productId);
		List<Product> productList = query.list();
		Product prod = productList.get(0);
		session.close();
		
		return prod;
	}
	
	@Override
	public void deleteProduct(String productId){
		Session session = this.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Product productToDelete = (Product) session.get(Product.class, productId);
		if(null != productToDelete){
			session.delete(productToDelete);
			tx.commit();
		}
		session.close();
	}

}
