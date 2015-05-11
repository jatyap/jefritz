package com.jatyap.jefritz.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jatyap.jefritz.model.SalesOrder;

public class SalesOrderDAOImpl extends BaseDAOImpl implements SalesOrderDAO {

	@Override
	public void saveOrder(SalesOrder order) {
		Session session = this.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.persist(order);

		tx.commit();
		session.close();
	}

	@Override
	@SuppressWarnings("unchecked")
	public SalesOrder getOrder(int orderId) {
		Session session = this.getSessionFactory().openSession();
		Query query = session.createQuery("from SalesOrder"
				+ " o where o.orderId = :orderId");
		query.setInteger("orderId", orderId);
		List<SalesOrder> results = query.list();
		SalesOrder result = results.get(0);
		session.close();
		return result;

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SalesOrder> getOrders() {
		Session session = this.getSessionFactory().openSession();
		Query query = session.createQuery("from SalesOrder");
		
		List<SalesOrder> results = query.list();
		session.close();
		return results;
	}



}
