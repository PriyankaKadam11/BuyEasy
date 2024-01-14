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
import com.jbk.exception.ResourseNotExistException;
import com.jbk.exception.SomethingWentWrongException;
import com.jbk.model.CategoryModel;
import com.jbk.service.CategoryService;

@RestController
@RequestMapping("/Category")
public class CategoryRestContoller {

	@Autowired
	private CategoryService service;

	@PostMapping("/add_Category")
	public ResponseEntity<String> addCategory(@RequestBody @Valid CategoryModel categorymodel) {
		int status = service.addCategory(categorymodel);

		if (status == 1) {
			return new ResponseEntity<String>("Category added successfully", HttpStatus.CREATED);
		} else if (status == 2) {
			throw new ResourceAlreadyExistException("Category alread exist please check unique fields");
		} else {
			throw new SomethingWentWrongException("Something went wrong");
		}

	}

	@GetMapping("/get-category-by-id/{categoryId}")
	public ResponseEntity<CategoryModel> getCategoryById(@PathVariable long categoryId) {
		CategoryModel categoryById = service.getCategoryById(categoryId);
		if (categoryById != null) {
			return new ResponseEntity<CategoryModel>(categoryById, HttpStatus.FOUND);
		} else {
			throw new ResourseNotExistException("category not found with id " + categoryId);
		}

	}

	@GetMapping("/get-all-categories")
	public ResponseEntity<List<CategoryModel>> getAllCategorys() {
		List<CategoryModel> allCategorys = service.getAllCategorys();
		if (allCategorys != null && !allCategorys.isEmpty()) {
			return new ResponseEntity<List<CategoryModel>>(allCategorys, HttpStatus.FOUND);
		} else {
			throw new ResourseNotExistException("category not found with id ");
		}
	}

	//deleted record but not showing on postman
	@DeleteMapping("/delete-perticular-categorybyId/{CategoryId}")
	public ResponseEntity<String> deleteCategory(@PathVariable("CategoryId") long CategoryId) {
		int delete = service.deleteCategory(CategoryId);
		if (delete == 1) {
			return new ResponseEntity<String>("Category deleted successfully||", HttpStatus.OK);
		} else {
			throw new ResourseNotExistException("no category available with id :" + CategoryId);
		}
	}

	@PutMapping("/update-perticular-category")
	public ResponseEntity<String> updateCategory(@RequestBody CategoryModel categorymodel) {
		service.updateCategory(categorymodel);
		return new ResponseEntity<String>("Category update successfully||", HttpStatus.OK);
	}
}
