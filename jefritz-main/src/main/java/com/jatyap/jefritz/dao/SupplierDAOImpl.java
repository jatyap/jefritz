package com.jatyap.jefritz.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.jatyap.jefritz.entity.Supplier;

public class SupplierDAOImpl implements SupplierDAO{

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void save(Supplier supplier) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(supplier);
		tx.commit();
		session.close();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Supplier> list() {
		Session session = this.sessionFactory.openSession();
		List<Supplier> supplierList = session.createQuery("from Supplier").list();
		session.close();
		return supplierList;
	}

	
}
