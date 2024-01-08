package com.jbk.service;

import java.util.List;

import com.jbk.model.ProductModel;

public interface ProductService {

	public int addProduct(ProductModel productmodel);
	public ProductModel getProductById(long productId);
	public List<ProductModel> getAllProducts();
	public void updateProduct(ProductModel productmodel);
	public int deleteProduct(long productId);
}
