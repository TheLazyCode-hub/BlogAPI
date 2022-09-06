package com.example.blogAPI.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blogAPI.config.ModelsMapping;
import com.example.blogAPI.entities.Category;
import com.example.blogAPI.exceptions.ResourceNotFoundException;
import com.example.blogAPI.payloads.CategoryDTO;
import com.example.blogAPI.repositories.CategoryRepository;
import com.example.blogAPI.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	CategoryRepository repo;
	
	@Autowired
	ModelsMapping mappers;
	
	
	@Override
	public List<CategoryDTO> getAllCategories() {
		List<Category> categories = repo.findAll();
		List<CategoryDTO> categorydto = categories.stream().map(category -> categoryToDTO(category)).collect(Collectors.toList());
		return categorydto;
	}

	@Override
	public CategoryDTO getCategoryById(int categoryId) {
		Category category = repo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
		return categoryToDTO(category);
	}

	@Override
	public CategoryDTO createCategory(CategoryDTO categordto) {
		Category category = dtoToCategory(categordto);
		Category savedCategory = repo.save(category);
		return categoryToDTO(savedCategory);
	}

	@Override
	public CategoryDTO updateCategory(CategoryDTO categorydto, int categoryId) {
		Category category = repo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
		category.setCategoryType(categorydto.getCategoryType());
		category.setCategoryDesc(categorydto.getCategoryDesc());
		Category updatedCategory = repo.save(category);
		CategoryDTO categoryDTO = categoryToDTO(updatedCategory);
		return categorydto;
	}

	@Override
	public void deleteCategory(int categoryId) {
		if(repo.existsById(categoryId)) {
			repo.deleteById(categoryId);
		}else {
			throw new ResourceNotFoundException("Category", "id", categoryId);
		}
	}
	
	private Category dtoToCategory(CategoryDTO categoryDTO) {
		Category category = mappers.modelMapper().map(categoryDTO, Category.class);
		return category;
	}
	
	private CategoryDTO categoryToDTO(Category category) {
		CategoryDTO dto  = mappers.modelMapper().map(category, CategoryDTO.class);
		return dto;
		
	}

}
