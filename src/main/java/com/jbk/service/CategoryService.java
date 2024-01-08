package com.jbk.service;

import java.util.List;

import com.jbk.model.CategoryModel;

public interface CategoryService {

	public int addCategory(CategoryModel categorymodel);
	public CategoryModel getCategoryById(long categoryId);
	public List<CategoryModel> getAllCategorys();
	public void updateCategory(CategoryModel categorymodel);
	public int deleteCategory(long categoryId);
}
