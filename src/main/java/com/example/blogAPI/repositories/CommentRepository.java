package com.example.blogAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blogAPI.entities.Comment;


public interface CommentRepository extends JpaRepository<Comment,Integer> {
}
