package com.example.blogAPI.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.blogAPI.payloads.APIresponse;
import com.example.blogAPI.payloads.CategoryDTO;
import com.example.blogAPI.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	CategoryService service;
	
	//Get
	@RequestMapping(method = RequestMethod.GET,value = "/")
	public ResponseEntity<List<CategoryDTO>> getAllCategories(){
		
		List<CategoryDTO> categories = service.getAllCategories();
		
		return new ResponseEntity<List<CategoryDTO>>(categories,HttpStatus.OK);
	}
	
	//GetbyID
		@RequestMapping(method = RequestMethod.GET,value = "/{categoryId}")
		public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable("categoryId") int categoryId){
			
			CategoryDTO category = service.getCategoryById(categoryId);
			
			return new ResponseEntity<CategoryDTO>(category,HttpStatus.OK);
		}
		
	//Create
		@RequestMapping(method = RequestMethod.POST, value = "/")
		public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categorydto){
			CategoryDTO category = service.createCategory(categorydto);
			return new ResponseEntity<CategoryDTO>(category,HttpStatus.CREATED);
		}
	
	//update
		@RequestMapping(method = RequestMethod.PUT,value = "/{categoryId}")
		public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO categorydto, @PathVariable("categoryId") int categoryId){
			CategoryDTO updatedCategoryDTO = service.updateCategory(categorydto, categoryId);
			return new ResponseEntity<CategoryDTO>(updatedCategoryDTO,HttpStatus.OK);
		}
		
	//Delete
		@RequestMapping(method = RequestMethod.DELETE,value = "/{categoryId}")
		public ResponseEntity<APIresponse> deleteCategory(@PathVariable("categoryId") int categoryId){
			service.deleteCategory(categoryId);
			return new ResponseEntity<APIresponse>(new APIresponse("Category deleted", true),HttpStatus.OK);
		}
		
		
		
		
		
		
		
		
		
		
		
}
