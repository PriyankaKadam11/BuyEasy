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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jbk.exception.ResourceAlreadyExistException;
import com.jbk.exception.ResourseNotExistException;
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
			throw new ResourseNotExistException("product not found with id " + productId);
		}

	}

	@GetMapping("/get-all-products")
	public ResponseEntity<List<ProductModel>> getAllProducts() {
		List<ProductModel> allProducts = service.getAllProducts();
		if (allProducts != null && !allProducts.isEmpty()) {
			return new ResponseEntity<List<ProductModel>>(allProducts, HttpStatus.FOUND);
		} else {
			throw new ResourseNotExistException("product not found with id ");
		}
	}

	@DeleteMapping("/delete-perticular-productbyId/{ProductId}")
	public ResponseEntity<String> deleteProduct(@PathVariable("ProductId") long ProductId) {
		int delete = service.deleteProduct(ProductId);
		if (delete == 1) {
			return new ResponseEntity<String>("Product deleted successfully||", HttpStatus.OK);
		} else {
			throw new ResourseNotExistException("no product available with id :" + ProductId);
		}
	}

	@PutMapping("/update-perticular-product")
	public ResponseEntity<String> updateProduct(@RequestBody ProductModel productmodel) {
		service.updateProduct(productmodel);
		return new ResponseEntity<String>("Product update successfully||", HttpStatus.OK);
	}

	@GetMapping("/sort-product-by-Name")
	public ResponseEntity<List<ProductModel>> sortProductByNm() {
		List<ProductModel> sortProductByName = service.sortProductByName();

		if (sortProductByName != null && !sortProductByName.isEmpty()) {
			return new ResponseEntity<List<ProductModel>>(sortProductByName, HttpStatus.FOUND);
		} else {
			throw new ResourseNotExistException("product not found with id ");

		}
	}

	@GetMapping("/get-maxprice-product")
	public ResponseEntity<List<ProductModel>> getMaxPriceProduct() {
		List<ProductModel> maxPriceProduct = service.getMaxPriceProduct();
		if (maxPriceProduct != null && !maxPriceProduct.isEmpty()) {
			return new ResponseEntity<List<ProductModel>>(maxPriceProduct, HttpStatus.FOUND);
		} else {
			throw new ResourseNotExistException("product not found with id ");

		}
	}

	@GetMapping("/get-maxprice")
	public ResponseEntity<Double> getMaxPrice() {
		double maxPrice = service.getMaxPrice();
		if (maxPrice != 0) {
			return new ResponseEntity<Double>(maxPrice, HttpStatus.FOUND);
		} else {
			throw new ResourseNotExistException("product not found with id ");

		}
	}

	@PostMapping("/upload-file")
	public ResponseEntity<String> uploadFile(@RequestParam MultipartFile file) {
		System.out.println(file.getOriginalFilename());
		String uploadFile = service.uploadFile(file);
		return new ResponseEntity<String>(uploadFile, HttpStatus.OK);
	}

	@GetMapping(value="/Product-Report", produces="application/json")
	public String productReport() {
		return service.generateReport();

	}

}