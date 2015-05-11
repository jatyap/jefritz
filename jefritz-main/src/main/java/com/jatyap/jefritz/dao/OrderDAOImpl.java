package com.jatyap.jefritz.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jatyap.jefritz.model.Order;
import com.jatyap.jefritz.model.OrderDetail;
import com.jatyap.jefritz.model.OrderType;

public class OrderDAOImpl extends BaseDAOImpl implements OrderDAO {

	@Override
	public void saveOrder(Order order) {
		Session session = this.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.persist(order);

		tx.commit();
		session.close();
	}
	
	public <E extends OrderDetail> void saveDetail(E detail){
		Session session = this.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.persist(detail);
		tx.commit();
		session.close();
	}

	@Override
	@SuppressWarnings("unchecked")
	public Order getOrder(OrderType orderType, int orderId) {
		Session session = this.getSessionFactory().openSession();
		Query query = session.createQuery("from " + orderType
				+ " o where o.orderId = :orderId");
		query.setInteger("orderId", orderId);
		List<Order> results = query.list();
		Order result = results.get(0);
		session.close();
		return result;

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Order> getOrders(OrderType orderType) {
		Session session = this.getSessionFactory().openSession();
		Query query = session.createQuery("from " + orderType);
		List<Order> results = query.list();
		session.close();
		return results;
	}

}
