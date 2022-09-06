package com.example.blogAPI.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blogAPI.config.ModelsMapping;
import com.example.blogAPI.entities.Comment;
import com.example.blogAPI.entities.Post;
import com.example.blogAPI.entities.User;
import com.example.blogAPI.exceptions.ResourceNotFoundException;
import com.example.blogAPI.payloads.CommentDTO;
import com.example.blogAPI.repositories.CommentRepository;
import com.example.blogAPI.repositories.PostRepository;
import com.example.blogAPI.repositories.UserRepository;
import com.example.blogAPI.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private PostRepository postRepo;
	
	@Autowired
	private CommentRepository commentRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ModelsMapping mappers;


	@Override
	public CommentDTO createComment(CommentDTO commentdto, int postId, int userId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		Comment comment = mappers.modelMapper().map(commentdto, Comment.class);
		comment.setPost(post);
		comment.setUser(user);
		
		Comment savedComment = commentRepo.save(comment);
		return mappers.modelMapper().map(savedComment, CommentDTO.class);
	}

	@Override
	public void deleteComment(int commentId) {
		Comment comment = commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
		commentRepo.delete(comment);

	}

}
