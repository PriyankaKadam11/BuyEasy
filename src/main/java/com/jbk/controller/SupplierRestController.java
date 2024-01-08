package com.jbk.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jbk.entity.SupplierEntity;
import com.jbk.exception.ResourceAlreadyExistException;
import com.jbk.exception.ResourseNotExist;
import com.jbk.exception.SomethingWentWrongException;
import com.jbk.model.SupplierModel;
import com.jbk.service.SupplierService;

@RestController
@RequestMapping("/Supplier")
public class SupplierRestController {

	@Autowired
	private SupplierService service;

	@PostMapping("/add_Supplier")
	public ResponseEntity<String> addSupplier(@RequestBody @Valid SupplierModel suppliermodel) {
		int status = service.addSupplier(suppliermodel);

		if (status == 1) {
			return new ResponseEntity<String>("Supplier added successfully", HttpStatus.CREATED);
		} else if (status == 2) {
			throw new ResourceAlreadyExistException("Supplier alread exist please check unique fields");
		} else {
			throw new SomethingWentWrongException("Something went wrong");
		}

	}

	@GetMapping("/get-supplier-by-id/{supplierId}")
	public ResponseEntity<SupplierModel> getSupplierById(@PathVariable long supplierId) {
		SupplierModel supplierById = service.getSupplierById(supplierId);
		if (supplierById != null) {
			return new ResponseEntity<SupplierModel>(supplierById, HttpStatus.FOUND);
		} else {
			throw new ResourseNotExist("supplier not found with id " + supplierId);
		}

	}

	@GetMapping("/get-all-suppliers")
	public ResponseEntity<List<SupplierModel>> getAllSuppliers() {
		List<SupplierModel> allSuppliers = service.getAllSuppliers();
		if (allSuppliers != null && !allSuppliers.isEmpty()) {
			return new ResponseEntity<List<SupplierModel>>(allSuppliers, HttpStatus.FOUND);
		} else {
			throw new ResourseNotExist("supplier not found with id ");
		}
	}

	@DeleteMapping("/delete-perticular-supplierbyId/{SupplierId}")
	public ResponseEntity<Object> deleteSupplier(@PathVariable("SupplierId") long SupplierId) {
		int deleteSupplier = service.deleteSupplier(SupplierId);
		if (deleteSupplier == 1) {
			return new ResponseEntity<Object>(deleteSupplier, HttpStatus.OK);
		} else if (deleteSupplier == 2) {
			return new ResponseEntity<Object>("no supplier available with id :" + SupplierId, HttpStatus.NOT_FOUND);
		} else {
			throw new SomethingWentWrongException("Something went wrong");
		}
	}

	@PutMapping("/update-perticular-supplier")
	public ResponseEntity<String> updateSupplier(@RequestBody SupplierModel suppliermodel) {
	    service.updateSupplier(suppliermodel);
			return new ResponseEntity<String>("Supplier update successfully||", HttpStatus.OK);
	}
	
	@GetMapping("/get-all-suppliers-usingCriteria")
	public ResponseEntity<List<SupplierModel>> getAllSuppliersCriteria() {
		List<SupplierModel> allSuppliers = service.getAllSuppliersCriteria();
		if (allSuppliers != null && !allSuppliers.isEmpty()) {
			return new ResponseEntity<List<SupplierModel>>(allSuppliers, HttpStatus.FOUND);
		} else {
			throw new ResourseNotExist("supplier not found");
		}
	}
	
	@GetMapping("/get-all-suppliers-usingRistriction")
	public ResponseEntity<List<SupplierModel>> getAllSupplierRistriction() {
		List<SupplierModel> allSuppliers = service.getAllSuppliersRistriction();
		if (allSuppliers != null && !allSuppliers.isEmpty()) {
			return new ResponseEntity<List<SupplierModel>>(allSuppliers, HttpStatus.FOUND);
		} else {
			throw new ResourseNotExist("supplier not found");
		}
	}
}
