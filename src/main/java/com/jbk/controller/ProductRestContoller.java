package com.jbk.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jbk.exception.ResourceAlreadyExistException;
import com.jbk.exception.ResourseNotExist;
import com.jbk.exception.SomethingWentWrongException;
import com.jbk.model.ProductModel;
import com.jbk.service.ProductService;

@RestController
@RequestMapping("/Product")
public class ProductRestContoller {

	@Autowired
	private ProductService service;

	@PostMapping("/add_Product")
	public ResponseEntity<String> addProduct(@RequestBody @Valid ProductModel productmodel) {
		int status = service.addProduct(productmodel);

		if (status == 1) {
			return new ResponseEntity<String>("Product added successfully", HttpStatus.CREATED);
		} else if (status == 2) {
			throw new ResourceAlreadyExistException("Product alread exist please check unique fields");
		} else {
			throw new SomethingWentWrongException("Something went wrong");
		}

	}

	@GetMapping("/get-product-by-id/{productId}")
	public ResponseEntity<ProductModel> getProductById(@PathVariable long productId) {
		ProductModel productById = service.getProductById(productId);
		if (productById != null) {
			return new ResponseEntity<ProductModel>(productById, HttpStatus.FOUND);
		} else {
			throw new ResourseNotExist("product not found with id " + productId);
		}

	}

	@GetMapping("/get-all-products")
	public ResponseEntity<List<ProductModel>> getAllProducts() {
		List<ProductModel> allProducts = service.getAllProducts();
		if (allProducts != null && !allProducts.isEmpty()) {
			return new ResponseEntity<List<ProductModel>>(allProducts, HttpStatus.FOUND);
		} else {
			throw new ResourseNotExist("product not found with id ");
		}
	}

	@DeleteMapping("/delete-perticular-productbyId/{ProductId}")
	public ResponseEntity<String> deleteProduct(@PathVariable("ProductId") long ProductId) {
		int delete = service.deleteProduct(ProductId);
		if (delete == 1) {
			return new ResponseEntity<String>("Product deleted successfully||", HttpStatus.OK);
		} else {
			throw new ResourseNotExist("no product available with id :" + ProductId);
		}
	}

	@PutMapping("/update-perticular-product")
	public ResponseEntity<String> updateProduct(@RequestBody ProductModel productmodel) {
		service.updateProduct(productmodel);
		return new ResponseEntity<String>("Product update successfully||", HttpStatus.OK);
	}
}