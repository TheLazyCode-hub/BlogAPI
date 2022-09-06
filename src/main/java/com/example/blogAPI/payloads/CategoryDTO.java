package com.example.blogAPI.payloads;

public class CategoryDTO {
	private int categoryId;
	private String categoryType;
	private String categoryDesc;
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryType() {
		return categoryType;
	}
	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}
	public String getCategoryDesc() {
		return categoryDesc;
	}
	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}
	public CategoryDTO(int categoryId, String categoryType, String categoryDesc) {
		this.categoryId = categoryId;
		this.categoryType = categoryType;
		this.categoryDesc = categoryDesc;
	}
	public CategoryDTO() {
	}
	
}
