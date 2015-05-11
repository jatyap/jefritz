package com.jatyap.jefritz.dao;

import java.util.List;

import com.jatyap.jefritz.model.SalesOrder;

public interface SalesOrderDAO {
	public List<SalesOrder> getOrders();
	public void saveOrder(SalesOrder order);
	public SalesOrder getOrder(int orderId);
}
