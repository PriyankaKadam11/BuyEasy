package com.jbk.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jbk.dao.daoImpl.ProductDaoImpl;
import com.jbk.entity.ProductEntity;
import com.jbk.exception.ResourseNotExistException;
import com.jbk.model.ProductModel;
import com.jbk.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDaoImpl product;

	@Autowired
	private ModelMapper mapper;

	@Override
	public int addProduct(ProductModel productmodel) {
		return product.addProduct(mapper.map(productmodel, ProductEntity.class));
	}

	@Override
	public ProductModel getProductById(long productId) {
		ProductModel map = null;
		ProductEntity productById = product.getProductById(productId);
		if (productById != null) {
			map = mapper.map(productById, ProductModel.class);
		}
		return map;
	}

	@Override
	public List<ProductModel> getAllProducts() {
		List<ProductModel> model = new ArrayList<>();
		List<ProductEntity> allProducts = product.getAllProducts();
		for (ProductEntity productEntity : allProducts) {
			ProductModel map = mapper.map(productEntity, ProductModel.class);
			model.add(map);
		}
		//allProducts.stream().map(list->allProducts(list,ProductModel.class)).collect(Collectors.toList());
		return model;
	}

	@Override
	public void updateProduct(ProductModel productmodel) {
		ProductEntity productById = product.getProductById(productmodel.getProductId());
		if (productById != null) {
				product.updateProduct(mapper.map(productmodel, ProductEntity.class));
		} else {
			throw new ResourseNotExistException("product not exist with id " + productmodel.getProductId());
		}
		
	}

	@Override
	public int deleteProduct(long productId) {
		return product.deleteProduct(productId);
	}

	@Override
	public List<ProductModel> sortProductByName() {
		List<ProductEntity> sortProductByName = product.sortProductByName();
		List<ProductModel> list =null;
		if(sortProductByName!=null && !sortProductByName.isEmpty()) {
			//for (ProductEntity entity : sortProductByName) {
			list = sortProductByName.stream().map(entity ->mapper.map(entity, ProductModel.class)).collect(Collectors.toList());
		//}
		}
		return list;
	}

	@Override
	public double getMaxPrice() {
		return product.getMaxPrice();
	}

	@Override
	public List<ProductModel> getMaxPriceProduct() {
		List<ProductEntity> maxPriceProduct = product.getMaxPriceProduct();
		List<ProductModel> productModel=null;
		if(maxPriceProduct!=null && !maxPriceProduct.isEmpty()) {
			
				productModel= maxPriceProduct.stream().map(entity1 ->mapper.map(entity1, ProductModel.class)).collect(Collectors.toList());
		  
		}else {
			throw new ResourseNotExistException("product not exist");
		}
		return productModel 	;
	}

}
