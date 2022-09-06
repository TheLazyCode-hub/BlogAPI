package com.example.blogAPI.services;

import java.util.List;

import com.example.blogAPI.payloads.CategoryDTO;

public interface CategoryService {
		//get
		List<CategoryDTO> getAllCategories();
		//getById
		CategoryDTO getCategoryById(int categoryId);
		//create
		CategoryDTO createCategory(CategoryDTO categordto);
		//update
		CategoryDTO updateCategory(CategoryDTO categorydto, int categoryId);
		//delete
		void deleteCategory(int categoryId);
}
