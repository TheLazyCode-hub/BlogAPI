package com.example.blogAPI.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.blogAPI.entities.Category;
import com.example.blogAPI.entities.Post;
import com.example.blogAPI.entities.User;


public interface PostRepository extends JpaRepository<Post, Integer>{
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	
	@Query("select p from Post p where p.postTitle like :key")
	Page<Post> searchByTitle(@Param("key") String title,Pageable page);
}
