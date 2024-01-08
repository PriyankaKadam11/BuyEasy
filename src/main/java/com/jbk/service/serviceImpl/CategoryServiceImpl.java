package com.jbk.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jbk.dao.daoImpl.CategoryDaoImpl;
import com.jbk.entity.CategoryEntity;
import com.jbk.exception.ResourseNotExist;
import com.jbk.model.CategoryModel;
import com.jbk.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryDaoImpl category;

	@Autowired
	private ModelMapper mapper;


	@Override
	public int addCategory(CategoryModel categorymodel) {
		return category.addCategory(mapper.map(categorymodel, CategoryEntity.class));
	}

	@Override
	public CategoryModel getCategoryById(long categoryId) {
		CategoryModel map = null;
		CategoryEntity categoryById = category.getCategoryById(categoryId);
		if (categoryById != null) {
			map = mapper.map(categoryById, CategoryModel.class);
		}
		return map;
	}

	@Override
	public List<CategoryModel> getAllCategorys() {
		List<CategoryModel> model = new ArrayList<>();
		List<CategoryEntity> allCategorys = category.getAllCategorys();
		for (CategoryEntity categoryEntity : allCategorys) {
			CategoryModel map = mapper.map(categoryEntity, CategoryModel.class);
			model.add(map);
		}
		//allCategorys.stream().map(list->allCategorys(list,CategoryModel.class)).collect(Collectors.toList());
		return model;
	}

	@Override
	public void updateCategory(CategoryModel categorymodel) {
		CategoryEntity categoryById = category.getCategoryById(categorymodel.getCategoryId());
		if (categoryById != null) {
				category.updateCategory(mapper.map(categorymodel, CategoryEntity.class));
		} else {
			throw new ResourseNotExist("category not exist with id " + categorymodel.getCategoryId());
		}
	}

	@Override
	public int deleteCategory(long categoryId) {
		return category.deleteCategory(categoryId);
	}

}
