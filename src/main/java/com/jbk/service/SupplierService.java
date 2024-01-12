package com.jbk.service;

import java.util.List;

import com.jbk.model.SupplierModel;

public interface SupplierService {

	public int addSupplier(SupplierModel suppliermodel);
	public SupplierModel getSupplierById(long supplierId);
	public List<SupplierModel> getAllSuppliers();
	public void updateSupplier(SupplierModel suppliermodel);
	public int deleteSupplier(long supplierId);
	public List<SupplierModel> getAllSuppliersCriteria();
	public List<SupplierModel> getAllSuppliersRistriction();
}
