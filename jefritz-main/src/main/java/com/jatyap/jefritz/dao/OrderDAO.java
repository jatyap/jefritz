package com.jatyap.jefritz.dao;

import java.util.List;

import com.jatyap.jefritz.entity.Order;
import com.jatyap.jefritz.entity.OrderDetail;
import com.jatyap.jefritz.entity.OrderType;

public interface OrderDAO {
	
	public List<Order> getOrders(OrderType orderType);
	public void saveOrder(Order order);
	public Order getOrder(OrderType orderType, int orderId);
	public <E extends OrderDetail> void saveDetail(E detail);
}
