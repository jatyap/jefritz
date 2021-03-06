package com.jatyap.jefritz.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.jatyap.jefritz.entity.Product;
import com.jatyap.jefritz.entity.ProductPricing;
import com.jatyap.jefritz.entity.ProductPricingType;

public abstract class BaseProductPricingDAOImpl implements ProductPricingDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private ProductPricingType pricingType = null;

	public ProductPricingType getPricingType() {
		return this.pricingType;
	}

	public void setPricingType(ProductPricingType pricingType) {
		this.pricingType = pricingType;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void savePricing(ProductPricing newPricing) {

		// TODO: Modify to create an arbitrary validFrom date for first entries
		String productId = newPricing.getProduct().getProductId();
		Date startDate = newPricing.getValidFrom();
		Date endDate = newPricing.getValidTo();
		if(null == startDate){
			throw new IllegalArgumentException("Pricing must have a valid start date");
		}
		ProductPricing beforePricing = this.getPricing(productId, startDate);
		ProductPricing afterPricing = null;
		afterPricing = (null != endDate) ? this.getPricing(productId, endDate)
				: null;

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		if (null != beforePricing) {
			session.load(beforePricing, beforePricing.getPricingId());
			beforePricing.setValidTo(startDate);
			session.persist(beforePricing);
		}

		if (null != afterPricing) {
			session.load(afterPricing, afterPricing.getPricingId());
			afterPricing.setValidFrom(endDate);
			session.persist(afterPricing);
		}

		// Load the product in the ProductPricing
		newPricing.setProduct((Product) session.get(Product.class, productId));

		session.persist(newPricing);
		tx.commit();
		session.close();
	}

	@Override
	@SuppressWarnings("unchecked")
	public ProductPricing getPricing(String productId, Date referenceDate) {
		Date refDate = referenceDate;
		ProductPricing result = null;
		if (null == refDate) {
			refDate = new Date();
		}
		Session session = this.getSessionFactory().openSession();
		String queryString = this.buildProductPricingQueryString();
		Query pricingQuery = session.createQuery(queryString);
		pricingQuery.setString("productId", productId);
		pricingQuery.setParameter("refDate", refDate);
		List<ProductPricing> pricings = pricingQuery.list();
		session.close();
		if (pricings.size() == 1) {
			result = pricings.get(0);
		}
		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ProductPricing> listProductPricing(Date referenceDate) {
		Date refDate = referenceDate;
		if (null == refDate) {
			refDate = new Date();
		}
		Session session = this.getSessionFactory().openSession();
		String queryString = this.buildPriceListQueryString();
		Query listQuery = session.createQuery(queryString);
		listQuery.setParameter("refDate", refDate);
		List<ProductPricing> pricings = listQuery.list();
		session.close();
		return pricings;
	}

	private String buildProductPricingQueryString() {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("from " + this.getPricingType() + " P");
		queryBuilder.append(" where P.product.productId = :productId");
		queryBuilder.append(" and :refDate > P.validFrom");
		queryBuilder
				.append(" and (:refDate <= P.validTo or P.validTo is null)");
		String queryString = queryBuilder.toString();
		return queryString;

	}

	private String buildPriceListQueryString() {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("from " + this.getPricingType() + " P");
		queryBuilder.append(" where :refDate > P.validFrom");
		queryBuilder
				.append(" and (:refDate <= P.validTo or P.validTo is null)");
		String queryString = queryBuilder.toString();
		return queryString;
	}

}
