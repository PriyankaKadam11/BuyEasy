package com.jbk.dao;

import java.util.List;

import com.jbk.entity.ProductEntity;

public interface ProductDao {

	public int addProduct(ProductEntity productentity);
	public ProductEntity getProductById(long productId);
	public List<ProductEntity> getAllProducts();
	public ProductEntity updateProduct(ProductEntity productentity);
	public int deleteProduct(long productId);
}
