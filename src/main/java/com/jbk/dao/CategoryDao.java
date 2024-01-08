package com.jbk.dao;

import java.util.List;

import com.jbk.entity.CategoryEntity;
import com.jbk.model.CategoryModel;

public interface CategoryDao {

	public int addCategory(CategoryEntity categoryentity);
	public CategoryEntity getCategoryById(long categoryId);
	public List<CategoryEntity> getAllCategorys();
	public CategoryEntity updateCategory(CategoryEntity categoryentity);
	public int deleteCategory(long categoryId);
}
