package com.jatyap.jefritz.dao;

import java.util.List;

import com.jatyap.jefritz.model.Supplier;

public interface SupplierDAO {

	public void save(Supplier supplier);
	public List<Supplier> list();
}
