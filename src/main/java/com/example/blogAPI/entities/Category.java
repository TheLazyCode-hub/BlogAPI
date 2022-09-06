package com.example.blogAPI.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "category_id")
	private int categoryId;

	@Column(name = "category_type", nullable = false)
	private String categoryType;

	@Column(name = "category_description", nullable = false)
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

	public Category(int categoryId, String categoryType, String categoryDesc) {
		this.categoryId = categoryId;
		this.categoryType = categoryType;
		this.categoryDesc = categoryDesc;
	}

	public Category() {
	}
	
	@OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
	public List<Post> posts = new ArrayList<>();
}
