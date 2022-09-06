package com.example.blogAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blogAPI.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
