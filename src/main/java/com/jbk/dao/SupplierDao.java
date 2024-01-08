package com.jbk.dao;

import java.util.List;

import com.jbk.entity.SupplierEntity;
import com.jbk.model.SupplierModel;

public interface SupplierDao {

	public int addSupplier(SupplierEntity supplierEntity);
	public SupplierEntity getSupplierById(long supplierId);
	public List<SupplierEntity> getAllSuppliers();
	public SupplierEntity updateSupplier(SupplierEntity suppliermodel);
	public int deleteSupplier(long supplierId);
	public List<SupplierModel> getAllSuppliersCriteria();
	public List<SupplierModel> getAllSuppliersRistriction();
}
