package com.jbk.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jbk.dao.daoImpl.SupplierDaoImpl;
import com.jbk.entity.SupplierEntity;
import com.jbk.exception.ResourseNotExistException;
import com.jbk.model.SupplierModel;
import com.jbk.service.SupplierService;

@Component
public class SupplierServiceImpl implements SupplierService {

	@Autowired
	private SupplierDaoImpl supplier;

	@Autowired
	private ModelMapper mapper;

	@Override
	public int addSupplier(SupplierModel suppliermodel) {
		return supplier.addSupplier(mapper.map(suppliermodel, SupplierEntity.class));
	}

	@Override
	public SupplierModel getSupplierById(long supplierId) {
		SupplierModel map = null;
		SupplierEntity supplierById = supplier.getSupplierById(supplierId);
		if (supplierById != null) {
			map = mapper.map(supplierById, SupplierModel.class);
		}
		return map;
	}

	// not completed
	@Override
	public List<SupplierModel> getAllSuppliers() {
		List<SupplierModel> model = new ArrayList<>();
		List<SupplierEntity> allSuppliers = supplier.getAllSuppliers();
		for (SupplierEntity supplierEntity : allSuppliers) {
			SupplierModel map = mapper.map(supplierEntity, SupplierModel.class);
			model.add(map);
		}
		//allSuppliers.stream().map(list->allSuppliers(list,SupplierModel.class)).collect(Collectors.toList());
		return model;
	}

	@Override
	public void updateSupplier(SupplierModel suppliermodel) {
		SupplierEntity supplierById = supplier.getSupplierById(suppliermodel.getSupplierId());
		if (supplierById != null) {
				supplier.updateSupplier(mapper.map(suppliermodel, SupplierEntity.class));
		} else {
			throw new ResourseNotExistException("supplier not exist with id " + suppliermodel.getSupplierId());
		}
	}

	@Override
	public int deleteSupplier(long supplierId) {

		return supplier.deleteSupplier(supplierId);
	}

	@Override
	public List<SupplierModel> getAllSuppliersCriteria() {
		
		return supplier.getAllSuppliersCriteria();
	}

	@Override
	public List<SupplierModel> getAllSuppliersRistriction() {
		return supplier.getAllSuppliersRistriction();
	}

}
