package com.jatyap.jefritz.dao;

import java.util.List;

import com.jatyap.jefritz.entity.Supplier;

public interface SupplierDAO {

	public void save(Supplier supplier);
	public List<Supplier> list();
}
